package com.maeteno.study.stream;

import com.maeteno.study.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
public class Main {

    public static void main(String[] args) {
        var list = Utils.mock(100);
        var newList = list.stream()
                .skip(10)
                .limit(30)
                .filter((Integer i) -> i % 2 == 0)
                .sorted()
                .peek((Integer i) -> log.info("{}", i))
                .dropWhile((Integer i) -> i == 22)
                .collect(Collectors.toList());

        log.info("{}", newList);
    }
}
