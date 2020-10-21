package com.maeteno.study.concurrent.example3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class CallableImpl implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        log.info("Callable Impl");
        return 1000;
    }
}
