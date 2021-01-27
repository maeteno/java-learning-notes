package com.maeteno.study.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

@Slf4j
public class SocketChannelDemo {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        // http://i1.ygimg.cn/pics/adidasneo/2019/101231471/101231471_01_f.png?2
        socketChannel.configureBlocking(false);
        boolean connect = socketChannel.connect(new InetSocketAddress("i1.ygimg.cn", 80));

        String newData =  "GET /pics/adidasneo/2019/101231471/101231471_01_f.png?2 HTTP/1.1\r\n" +
                "Host: i1.ygimg.cn\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "DNT: 1\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 11_1_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.96 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Accept-Encoding: gzip, deflate\r\n" +
                "Accept-Language: zh-CN,zh;q=0.9,en;q=0.8\r\n" +
                "If-None-Match: \"5d5e01ff-aee87\"\r\n" +
                "If-Modified-Since: Thu, 22 Aug 2019 02:46:23 GMT\r\n" +
                "\r\n";

        ByteBuffer writeBuf = ByteBuffer.allocate(newData.length());
        writeBuf.clear();
        writeBuf.put(newData.getBytes());
        writeBuf.flip();
        while (writeBuf.hasRemaining()) {
            socketChannel.write(writeBuf);
        }

        ByteBuffer readBuf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(readBuf);

        log.warn("bytesRead :{}", bytesRead);

        while (bytesRead != -1) {
            readBuf.flip();

            while (readBuf.hasRemaining()) {
                log.info("Data: {}", readBuf.get());
            }

            readBuf.clear();
            bytesRead = socketChannel.read(readBuf);
        }

        socketChannel.close();
    }
}
