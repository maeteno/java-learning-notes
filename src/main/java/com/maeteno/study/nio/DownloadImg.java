package com.maeteno.study.nio;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DownloadImg {

    @Data
    @Builder
    public static class Img {
        private String path;
        private String name;
    }

    @Data
    @Builder
    public static class Info {
        private List<Img> img;
        private int index;
        private boolean isSend;
    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        URL u = new URL("http://i1.ygimg.cn");
        String host = u.getHost();
        int port = 80;
        SocketAddress remote = new InetSocketAddress(host, port);

        Selector selector = Selector.open();

        String path1 = "/pics/adidasneo/2019/101231471/101231471_01_f.png?2";
        String path2 = "/pics/adidasneo/2019/101231471/101231471_01_001_f.png?2";

        int index = 0;
        while (index < 100) {
            SocketChannel socketChannel = SocketChannel.open(remote);
            socketChannel.configureBlocking(false);
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            Info info = Info.builder()
                    .index(0)
                    .isSend(false)
                    .build();

            List<Img> imges = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                imges.add(Img.builder()
                        .path(path1)
                        .name(index + "-00.png")
                        .build());
                index++;
            }
            info.setImg(imges);
            selectionKey.attach(info);
        }

        while (selector.select(10L) > 0) {
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                SocketChannel socketChannel = (SocketChannel) key.channel();
                Info info = (Info) key.attachment();

                List<Img> imges = info.getImg();
                int index1 = info.getIndex();
                Img img = imges.get(index1);

                if (info.isSend() && key.isReadable()) {
                    log.info("isReadable:{}", img.getName());

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    ByteArrayOutputStream body = new ByteArrayOutputStream();
                    ByteArrayOutputStream header = new ByteArrayOutputStream();
                    FileOutputStream out = new FileOutputStream(img.getName());
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
                                log.info("read end...");
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
                        out.write(body.toByteArray());
                        info.setSend(false);
                    } catch (IOException exception) {
                        log.error("图片下载失败：", exception);
                    } finally {
                        body.close();
                        header.close();
                        index1++;

                        if (index1 >= imges.size()) {
                            socketChannel.close();
                            info.setSend(true);
                        } else {
                            info.setIndex(index1);
                        }
                    }
                }

                if (!info.isSend() && key.isWritable()) {
                    log.info("isWritable");

                    String request = "GET " + img.getPath() + " HTTP/1.1\r\n"
                            + "Host: " + host + "\r\n"
                            + "Accept: image/*\r\n"
                            + "Connection: keep-alive\r\n"
                            + "User-Agent: JavaNIO\r\n"
                            + "\r\n";

                    ByteBuffer header = ByteBuffer.wrap(request.getBytes(StandardCharsets.US_ASCII));
                    socketChannel.write(header);
                    info.setSend(true);
                }

                keyIterator.remove();
            }
        }

        selector.close();

        long time = System.currentTimeMillis() - start;

        log.info("Time: {} s", time / 1000);
    }
}
