package com.maeteno.study.future.example3;

import java.util.concurrent.CompletableFuture;

public class CombineDemo {
    public static void main(String[] args) {
        var f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        var f2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });

        var f3 = f1.thenCombineAsync(f2, Integer::sum);

        System.out.print(f3.join());
    }
}
