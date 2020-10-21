package com.maeteno.study.future.example2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class Demo {

    public static void main(String[] args) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        var result = future.join();
        // var result = future.get();

        log.info("{}", result);
    }
}
