package com.maeteno.study.concurrent.example3;

import java.awt.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable c = new CallableImpl();
        FutureTask<Integer> f = new FutureTask<>(c);

        new Thread(f).start();
        System.out.println("Main 1");

        var result = f.get();
        System.out.println(result);

        System.out.println("Main End");
    }
}
