package com.maeteno.study.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DemoBIO {

    public static void main(String[] args) throws IOException {
        File file = new File("log/bio-demo.txt");

        // Java中的BIO对IO的处理是安IO流的方式处理的。通过输入输出流对文件进行操作
        // 流分为字节流和字符流， 顾名思义，他们分别安字符或字符为单位操作文件

        // 字节流
        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] content = "BIO DEMO".getBytes();
            outputStream.write(content);
        }

        try (FileInputStream inputStream = new FileInputStream(file)) {
            int b = -1;
            while ((b = inputStream.read()) != -1) {
                System.out.print(b);
            }
            System.out.println();
        }

        // 字符流
        try (Writer write = new FileWriter(file, StandardCharsets.UTF_8, true)) {
            write.write("\nHELLO WORD 你好 👋\n");
        }

        // 字符流
        try (Reader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
