package com.maeteno.study.concurrent.example4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Main {

    public static void main(String[] args) {
        var pool = new ThreadPoolExecutor(50, 100, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        // var pool2 = Executors.newFixedThreadPool(10);

        var sum = new AtomicInteger();

        var index = 0;
        while (index++ < 100) {
            pool.execute(() -> {
                log.info("Pool: " + sum.addAndGet(1));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.info("ERROR");
                    e.printStackTrace();
                }
            });
        }

        var count = pool.getActiveCount();

        log.info("{}", count);

        pool.shutdown();

        log.info("END");
    }
}
