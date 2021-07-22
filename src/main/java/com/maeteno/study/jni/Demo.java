package com.maeteno.study.jni;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@Slf4j
public class Demo {

    @SneakyThrows
    public static void main(String[] args) {
        Process process = Runtime.getRuntime().exec("ls -al");

        InputStream inputStream = process.getInputStream();

        log.info("{}", inputStream.readAllBytes());

        System.out.println(inputStream.readAllBytes());
    }
}
