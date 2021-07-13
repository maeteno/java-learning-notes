package com.maeteno.study.iostream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class Demo1 {
    public static void main(String[] args) {
        URL url = Demo.class.getResource("/demo.text");
        File file = new File(Objects.requireNonNull(url).getPath());

        try (InputStream inputStream = new FileInputStream(file)) {
            // 读取数据到字节数组
            byte[] buf = new byte[256];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                System.out.println(length);
                System.out.print(new String(buf, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n===============");
        try (InputStream inputStream = new FileInputStream(file)) {
            // 一次性全部读取
            byte[] readAllBytes = inputStream.readAllBytes();
            System.out.print(new String(readAllBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
