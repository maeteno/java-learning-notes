package com.maeteno.study.jvm;

import java.lang.reflect.Method;

public class ReflectionTest {
    private static int count = 0;

    public static void foo() {
        new Exception("test#" + (count++)).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        Class<?> clz = Class.forName("com.maeteno.study.jvm.ReflectionTest");
        Method method = clz.getMethod("foo");
        for (int i = 0; i < 20; i++) {
            method.invoke(null);
        }
    }
}
