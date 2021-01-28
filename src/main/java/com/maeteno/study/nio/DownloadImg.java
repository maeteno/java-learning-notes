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
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class DownloadImg {

    @Builder
    @Data
    public static class Info {
        private String path;
        private String name;
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
        while (index++ < 100) {
            SocketChannel socketChannel = SocketChannel.open(remote);
            socketChannel.configureBlocking(false);
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            String path = path1;
            if (index % 5 == 0) {
                path = path2;
            }

            selectionKey.attach(
                    Info.builder()
                            .path(path)
                            .name("00" + index + ".png")
                            .isSend(false)
                            .build()
            );
        }

        while (selector.select(10L) > 0) {
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                SocketChannel socketChannel = (SocketChannel) key.channel();
                Info info = (Info) key.attachment();

                if (info.isSend() && key.isReadable()) {
                    log.info("isReadable:{}", info.getName());

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                    FileOutputStream out = new FileOutputStream(info.getName());
                    try {
                        // 根据连续换行符截取消息体
                        int tag = 0;
                        while (socketChannel.read(buffer) != -1) {
                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                byte b = buffer.get();
                                if (tag >= 4) {
                                    byteOut.write(b);
                                } else {
                                    if (b == '\r' || b == '\n') {
                                        tag += 1;
                                    } else {
                                        tag = 0;
                                    }
                                }
                            }

                            buffer.clear();
                        }
                        out.write(byteOut.toByteArray());
                    } catch (IOException exception) {
                        log.error("图片下载失败：", exception);
                    } finally {
                        byteOut.close();
                        socketChannel.close();
                        socketChannel.close();
                    }
                }

                if (!info.isSend() && key.isWritable()) {
                    log.info("isWritable");

                    String request = "GET " + info.getPath() + " HTTP/1.1\r\n"
                            + "Host: " + host + "\r\n"
                            + "Accept: image/*\r\n"
                            + "Connection: close\r\n"
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
