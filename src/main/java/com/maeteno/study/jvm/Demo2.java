package com.maeteno.study.jvm;

public class Demo2 {

    public static void main(String[] args) {
        Runnable r1 = () -> {
            System.out.println("hello, lambda");
        };
        r1.run();

        Runnable r2 = () -> {
            System.out.println("hello, lambda");
        };
        r2.run();
    }
}
