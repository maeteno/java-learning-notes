package com.maeteno.study.concurrent.example2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadImpl extends Thread {
    private int index = 0;

    @Override
    public void run() {
        while (index < 10) {
            log.info("Thread => Index:" + index);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                index++;
            }
        }
    }
}
