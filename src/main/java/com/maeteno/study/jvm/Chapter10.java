package com.maeteno.study.jvm;

public class Chapter10 {
    public static void foo() {
        int i = 0;
        for (int j = 0; j < 50; j++)
            i = i++;
        System.out.println(i);
    }

    public static void bar() {
        int i = 0;
        i = i++ + ++i;
        System.out.println("i=" + i);
    }

    public static void main(String[] args) {
        foo();
        bar();
    }
}

