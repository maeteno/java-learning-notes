package com.maeteno.study.concurrent.example3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable c = new CallableImpl();

        FutureTask<Integer> f = new FutureTask<Integer>(c);

        new Thread(f).start();
        log.info("Main 1");
        var result = f.get();
        log.info("{}", result);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future f2 = executor.submit(c);
        var result2 = f2.get();
        log.info("{}", result2);

        log.info("Main End");
    }
}
