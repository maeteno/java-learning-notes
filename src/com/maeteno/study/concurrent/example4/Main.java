package com.maeteno.study.concurrent.example4;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        var pool = new ThreadPoolExecutor(5, 10, 10L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        var index = 0;
        while (index++ < 10) {
            pool.execute(() -> System.out.println("Pool"));
        }

       var count =  pool.getActiveCount();

        System.out.println(count);

        pool.shutdown();
    }
}
