package com.maeteno.study.concurrent.example4;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        var pool = new ThreadPoolExecutor(50, 100, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        // var pool2 = Executors.newFixedThreadPool(10);

        var sum = new AtomicInteger();

        var index = 0;
        while (index++ < 1000) {
            pool.execute(() -> {
                System.out.println("Pool: " + sum.addAndGet(1));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("ERROR");
                    e.printStackTrace();
                }
            });
        }

        var count = pool.getActiveCount();

        System.out.println(count);

        pool.shutdown();

        System.out.println("END");
    }
}
