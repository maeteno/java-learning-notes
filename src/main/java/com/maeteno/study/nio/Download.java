package com.maeteno.study.nio;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Download implements Closeable {
    @Data
    @Builder
    private static class Attach {
        private int index;
        private long start;
        private String name;
        private boolean send;
    }

    private final String host;
    private final int size;
    private final int port;
    private final SocketAddress remote;
    private final List<SocketChannel> channels;

    private Selector selector;

    public Download(String host) throws IOException {
        this(host, 2);
    }

    public Download(String host, int size) throws IOException {
        URL u = new URL("http://i1.ygimg.cn");
        this.host = u.getHost();
        this.size = size;
        this.port = 80;
        this.channels = new ArrayList<>();
        this.remote = new InetSocketAddress(this.host, port);
    }

    public List<ByteArrayOutputStream> down(List<Image> images) throws IOException {
        initSocketChannel();
        List<ByteArrayOutputStream> result = new ArrayList<>();

        int complete = 0;
        int now = 0;
        int total = images.size();
        while (selector.select(10L) > 0) {
            if (complete >= total) {
                break;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                SocketChannel socketChannel = (SocketChannel) key.channel();
                Attach attach = (Attach) key.attachment();

                if (!attach.isSend() && complete < total && key.isReadable()) {
                    log.info("isReadable...");
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    ByteArrayOutputStream body = new ByteArrayOutputStream();
                    ByteArrayOutputStream header = new ByteArrayOutputStream();

                    try {
                        // 根据连续换行符截取消息体
                        int tag = 0;
                        int length = 0;
                        int bodyLength = 0;
                        while (socketChannel.read(buffer) != -1) {
                            buffer.flip();
                            if (tag >= 4 && length == 0) {
                                String reg = "Content-Length: (\\d*)";
                                Matcher m = Pattern.compile(reg).matcher(header.toString());
                                if (m.find()) {
                                    length = Integer.valueOf(m.group(1));
                                }
                            }

                            if (length > 0 && bodyLength >= length) {
                                break;
                            }

                            while (buffer.hasRemaining()) {
                                byte b = buffer.get();
                                if (tag >= 4) {
                                    bodyLength++;
                                    body.write(b);
                                } else {
                                    if (b == '\r' || b == '\n') {
                                        tag += 1;
                                    } else {
                                        tag = 0;
                                    }
                                    header.write(b);
                                }
                            }
                            buffer.clear();
                        }

                        result.add(body);
                        attach.setSend(true);
                    } catch (IOException exception) {
                        log.error("图片下载失败：", exception);
                        socketChannel.close();
                        replace(attach.getIndex());
                    } finally {
                        header.close();
                        complete++;
                    }
                }

                if (attach.isSend() && now < total && key.isWritable()) {
                    log.info("isWritable...");
                    Image image = images.get(now++);
                    ByteBuffer header = builderHeader(image.getPath());
                    socketChannel.write(header);
                    attach.setName(image.getName());
                    attach.setStart(System.currentTimeMillis());
                    attach.setSend(false);
                }

                if (!attach.isSend() && System.currentTimeMillis() - attach.getStart() > 500L) {
                    log.info("Time Out...");
                    socketChannel.close();
                    replace(attach.getIndex());
                    complete++;
                }

                keyIterator.remove();
            }
        }

        return result;
    }

    private void replace(int index) throws IOException {
        channels.remove(index);
        SocketChannel socketChannel = builder(index);
        log.info("replace channel {}", index);
        channels.add(index, socketChannel);
    }

    private ByteBuffer builderHeader(String path) {
        String header = "GET " + path + " HTTP/1.1\r\n"
                + "Host: " + host + "\r\n"
                + "Accept: image/*\r\n"
                + "Connection: keep-alive\r\n"
                + "User-Agent: JavaNIO\r\n"
                + "\r\n";

        return ByteBuffer.wrap(header.getBytes(StandardCharsets.US_ASCII));
    }


    private void initSocketChannel() throws IOException {
        this.selector = Selector.open();
        int index = 0;
        while (index < size) {
            SocketChannel socketChannel = builder(index);
            channels.add(index, socketChannel);
            index++;
        }
    }

    private SocketChannel builder(int index) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(remote);
        socketChannel.configureBlocking(false);
        channels.add(socketChannel);

        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        Attach attach = Attach.builder().index(index).send(true).build();
        selectionKey.attach(attach);
        return socketChannel;
    }

    @Override
    public void close() throws IOException {
        log.info("close...");
        if (Objects.nonNull(selector) && selector.isOpen()) {
            selector.close();
        }

        channels.forEach(channel -> {
            try {
                channel.close();
            } catch (IOException e) {
                log.warn("channel close err: {}",e.getMessage());
            }
        });
    }
}
