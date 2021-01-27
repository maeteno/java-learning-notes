package com.maeteno.study.nio;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

// http://www.java2s.com/Tutorials/Java/Socket/How_to_use_Java_SocketChannel_create_a_HTTP_client.htm
public class HttpDemo {
    public static void main(String[] args) throws IOException {

        URL u = new URL("http://i1.ygimg.cn");
        String host = u.getHost();
        int port = 80;
        String path = "/pics/adidasneo/2019/101231471/101231471_01_f.png?2";

        SocketAddress remote = new InetSocketAddress(host, port);
        SocketChannel channel = SocketChannel.open(remote);

        FileOutputStream out = new FileOutputStream("101231471_01_f.png");

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        String request = "GET " + path + " HTTP/1.1\r\n" + "User-Agent: HTTPGrab\r\n"
                + "Accept: text/*\r\n" + "Connection: close\r\n" + "Host: " + host + "\r\n" + "\r\n";

        ByteBuffer header = ByteBuffer.wrap(request.getBytes(StandardCharsets.US_ASCII));
        channel.write(header);

        ByteBuffer buffer = ByteBuffer.allocate(8192);

        // 根据连续换行符截取消息体
        int tag = 0;
        while (channel.read(buffer) != -1) {
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
        channel.close();
    }
}
