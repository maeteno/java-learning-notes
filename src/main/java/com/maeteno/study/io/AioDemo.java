package com.maeteno.study.io;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

// https://blog.csdn.net/u010541670/article/details/91890649
@Slf4j
public class AioDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousSocketChannel.open();

        try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
                Paths.get("log/asynchronous.txt"),
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE)
        ) {
            CompletionHandler<Integer, Object> handler = new CompletionHandler<>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    log.info("Attachment: {} {} bytes written",
                            attachment,
                            result
                    );

                    log.info("CompletionHandler Thread ID: {} Name:{}",
                            Thread.currentThread().getId(),
                            Thread.currentThread().getName()
                    );
                }

                @Override
                public void failed(Throwable e, Object attachment) {
                    log.error("Attachment: {} failed with:", attachment, e);
                }
            };

            log.info("Main Thread ID: {}", Thread.currentThread().getId());

            fileChannel.write(
                    ByteBuffer.wrap("Sample".getBytes()),
                    0,
                    "First Write",
                    handler
            );

            fileChannel.write(
                    ByteBuffer.wrap("Box".getBytes()),
                    6,
                    "Second Write",
                    handler
            );

            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            log.error("InterruptedException: \n", e);
            throw e;
        }
    }
}
