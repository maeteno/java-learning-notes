package com.maeteno.study.jvm;

public class Demo {

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello, inner class");
            }
        };
        r1.run();
    }
}
