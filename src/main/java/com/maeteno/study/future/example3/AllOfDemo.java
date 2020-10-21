package com.maeteno.study.future.example3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AllOfDemo {

    public static void main(String[] args) {
        var fArr = new CompletableFuture[1000];
        var sum = new AtomicInteger(0);
        var ex = Executors.newFixedThreadPool(100);

        for (int index = 0; index < 1000; index++) {
            fArr[index] = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                var val = sum.addAndGet(1);

                System.out.println(Thread.currentThread().getId() + " : " + val);
                return val;
            }, ex);
        }

        var future = CompletableFuture.allOf(fArr);
        future.join();

        System.out.print(sum.get());

        ex.shutdown();
    }
}
