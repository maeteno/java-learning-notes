package com.maeteno.study.future.example2;

import java.util.concurrent.CompletableFuture;

public class Demo {
    public static void main(String[] args) {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        var result = future.join();
        // var result = future.get();

        System.out.println(result);
    }
}
