package com.maeteno.study.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/tmp/nio-date.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        Selector selector = Selector.open();

        selector.select();
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            if (key.isAcceptable()) {
                key.channel().close();
                // a connection was accepted by a ServerSocketChannel.
            } else if (key.isConnectable()) {
                key.channel().close();
                log.info("isConnectable");
                // a connection was established with a remote server.
            } else if (key.isReadable()) {
                key.channel().close();
                log.info("isReadable");
                // a channel is ready for reading
            } else if (key.isWritable()) {
                key.channel().close();
                log.info("isWritable");
                // a channel is ready for writing
            }
            keyIterator.remove();
        }
    }
}
