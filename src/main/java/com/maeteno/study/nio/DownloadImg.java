package com.maeteno.study.nio;

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
    public static void main(String[] args) throws IOException {
        URL u = new URL("http://i1.ygimg.cn");
        String host = u.getHost();
        int port = 80;
        SocketAddress remote = new InetSocketAddress(host, port);

        Selector selector = Selector.open();

        String path = "/pics/adidasneo/2019/101231471/101231471_01_f.png?2";
        SocketChannel socketChannel1 = SocketChannel.open(remote);
        socketChannel1.configureBlocking(false);
        socketChannel1.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        while (selector.select() > 0) {
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                SocketChannel socketChannel = (SocketChannel) key.channel();

                if (key.isAcceptable()) {
                    log.info("isAcceptable");
                }
                if (key.isConnectable()) {
                    log.info("isConnectable");
                }

                if (key.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(8192);
                    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                    FileOutputStream out = new FileOutputStream("101231471_01_f.png");

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
                    out.close();
                    socketChannel.close();
                } else if (key.isWritable()) {
                    String request = "GET " + path + " HTTP/1.1\r\n"
                            + "Host: " + host + "\r\n"
                            + "Accept: image/*\r\n"
                            + "Connection: close\r\n"
                            + "User-Agent: HTTPGrab\r\n"
                            + "\r\n";

                    ByteBuffer header = ByteBuffer.wrap(request.getBytes(StandardCharsets.US_ASCII));
                    socketChannel.write(header);
                }

                keyIterator.remove();
            }
        }

        selector.close();
    }
}
