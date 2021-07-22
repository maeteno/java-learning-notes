package com.maeteno.study.io.inputstream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Slf4j
public class Demo1 {

    @SneakyThrows
    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Path.of("log/bio-demo.txt"))) {
            lines.map(line -> line + "---\n")
                    .forEach(log::info);
        }

        String readString = Files.readString(Path.of("log/bio-demo.txt"));
        log.info(readString);
    }
}
