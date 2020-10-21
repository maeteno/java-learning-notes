package com.maeteno.study.concurrent.example3;

import java.util.concurrent.Callable;

public class CallableImpl implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable Impl");
        return 1000;
    }
}
