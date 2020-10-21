package com.maeteno.study.future.example3;

import java.util.concurrent.CompletableFuture;

public class Demo2 {
    public static void main(String[] args) {
        var cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        // 获取结果
        cf1.whenCompleteAsync((v, e) -> {
            System.out.print(Thread.currentThread().getId());
            System.out.print(" :whenCompleteAsync: ");
            System.out.println(v);
        });

        // 转换为一个新的CompletableFuture
        cf1.thenApplyAsync(i -> {
            System.out.print(Thread.currentThread().getId());
            System.out.print(" :thenApplyAsync: ");
            System.out.println(i);
            return i + 1;
        });

        // 接收结果消费
        cf1.thenAcceptAsync(i -> {
            System.out.print(Thread.currentThread().getId());
            System.out.print(" :thenAcceptAsync: ");
            System.out.println(i);
        });

        cf1.thenComposeAsync(i -> {
            System.out.print(Thread.currentThread().getId());
            System.out.print(" :thenComposeAsync: ");
            System.out.println(i);

            return CompletableFuture.supplyAsync(() -> (i * 10));
        });

        System.out.println("Main: 37");
        cf1.join();
        System.out.print(Thread.currentThread().getId());
        System.out.println(" :Main: END");
    }
}
