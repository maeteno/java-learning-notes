package com.maeteno.study.concurrent.example2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Thread tt1 = new ThreadImpl();
        tt1.start();

        Thread tt2 = new ThreadImpl();
        tt2.start();

        log.info("Main Class End");
    }
}
