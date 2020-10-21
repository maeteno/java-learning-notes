package com.maeteno.study.concurrent.example1;

public class RunnableImpl implements Runnable {
    private String name;
    private int index;

    public RunnableImpl(String name) {
        this.name = name;
        this.index = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index < 10) {
            System.out.println(this.name + " => Index:" + index);
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
