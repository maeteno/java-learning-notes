package com.maeteno.study.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/tmp/nio-date.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);

        log.info("Read...");

        int bytesRead = inChannel.read(buf);

        log.warn("bytesRead :{}", bytesRead);

        while (bytesRead != -1) {
            buf.flip();

            while (buf.hasRemaining()) {
                log.info("b: {}", buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }

        aFile.close();
    }
}
