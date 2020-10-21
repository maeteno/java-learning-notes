package com.maeteno.study.future.example3;

import java.util.concurrent.CompletableFuture;

public class Demo {
    public static void main(String[] args) {
        var cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        var cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });

        cf1.whenCompleteAsync((v, e) -> {
            System.out.println("whenCompleteAsync");
            System.out.println(v);
        });

        cf1.thenApplyAsync((i) -> {
            System.out.print("thenApplyAsync:");
            System.out.println(i);
            return i + 1;
        });

        cf2.whenCompleteAsync((v, e) -> {
            System.out.println(v);
        });

        var cf3 = CompletableFuture.allOf(cf1, cf2);

        cf3.join();
        // while (!cf3.isDone()) ;
    }
}
