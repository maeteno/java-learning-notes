package com.maeteno.study.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Flat {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("Word");
        String uniqueCharacters = words.stream().map(w -> w.split("")) // ─将每个单词转换为由其字母构成的数组
                .flatMap(Arrays::stream) // 将各个生成流扁平化为单个流
                .distinct()
                .collect(Collectors.joining("-"));

        System.out.println(uniqueCharacters);
    }
}