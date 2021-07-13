package com.maeteno.study.iostream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Demo {
    public static void main(String[] args) throws FileNotFoundException {
        var url = Demo.class.getResource("/demo.text");
        var file = new File(Objects.requireNonNull(url).getPath());

        try (var fileOutputStream = new FileOutputStream(file)) {
            byte[] bytes = new byte[]{'a', 'b', 'c', 'e', 'f'};

            // 安单个字节写入
            for (byte aByte : bytes) {
                fileOutputStream.write(aByte);
            }
            fileOutputStream.write('\n');

            // 写入一个字节数组
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        var inputStream = Demo.class.getResourceAsStream("/demo.text");
//        var streamReader = new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.ISO_8859_1);
//
//        String encoding = streamReader.getEncoding();
//
//        log.info("encoding: {}", encoding);
    }
}
