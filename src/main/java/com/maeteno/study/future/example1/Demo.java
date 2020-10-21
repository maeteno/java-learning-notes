package com.maeteno.study.future.example1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(10);

        Future<String> f = es.submit(() -> {
            Thread.sleep(500);
            return "Demo";
        });


        System.out.println(f.get());

        es.shutdown();
    }
}
