package com.maeteno.study.jvm;

public class Chapter17<T> {
    public T foo(T s) {
        int i = 0;
        for (int j = 0; j < 50; j++)
            i = i++;
        System.out.println(i);
        return s;
    }

    public static void bar(String demo) {
        int i = 0;
        i = i++ + ++i;
        System.out.println("i=" + i + demo);
    }

    public static void main(String[] args) {
        Chapter17<Integer> chapter17 = new Chapter17<>();
        chapter17.foo(123);
        bar("123");
    }
}

