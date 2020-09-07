package com.maeteno.study.concurrent.example2;

public class ThreadImpl extends Thread {
    private int index = 0;

    @Override
    public void run() {
        while (index < 10) {
            System.out.println("Thread => Index:" + index);
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
