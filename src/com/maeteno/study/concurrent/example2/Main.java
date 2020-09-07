package com.maeteno.study.concurrent.example2;

public class Main {
    public static void main(String[] args) {
        Thread tt1 = new ThreadImpl();
        tt1.start();

        Thread tt2 = new ThreadImpl();
        tt2.start();

        System.out.println("Main Class End");
    }
}
