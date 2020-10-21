package com.maeteno.study.concurrent.example1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        RunnableImpl r = new RunnableImpl("Demo-1");

        Thread t = new Thread(r);
        t.start();

        r.setName("Update name");
        Thread t2 = new Thread(r);
        t2.start();

        Thread t3 = new Thread(Main::run);
        t3.start();

        log.info("Main Class End");
    }

    private static void run() {
        int index = 0;
        while (index++ < 10) {
            log.info("Lambda => Index:" + index);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
