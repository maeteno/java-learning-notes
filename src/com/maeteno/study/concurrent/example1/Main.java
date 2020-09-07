package com.maeteno.study.concurrent.example1;

public class Main {
    public static void main(String[] args) {
        RunnableImpl r = new RunnableImpl("Demo-1");

        Thread t = new Thread(r);
        t.start();

        r.setName("Update name");
        Thread t2 = new Thread(r);
        t2.start();

        Thread t3 = new Thread(() -> {
            int index = 0;
            while (index++ < 10) {
                System.out.println("Lambda => Index:" + index);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t3.start();

        System.out.println("Main Class End");
    }
}
