package com.maeteno.study.stream;

import com.maeteno.study.utils.Utils;

import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        var list = Utils.mock(100);
        var newList = list.stream().skip(10).limit(30)
                .filter((Integer i) -> i % 2 == 0).sorted().peek((Integer i) -> System.out.println(i))
                .dropWhile((Integer i) -> i == 22)
                .collect(Collectors.toList());

        System.out.print(newList);
    }
}
