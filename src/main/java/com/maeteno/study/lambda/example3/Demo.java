package com.maeteno.study.lambda.example3;

import java.util.function.Function;

public class Demo {
    public static void main(String[] args) {
        func(() -> new Class1());
        func(Class1::new);

        func2((Integer i) -> new Class1(i));
        func2(Class1::new);

        Function<Integer, Class1> f = Class1::new;
        func2(f);
    }

    public static void func(FunctionI i) {
        var obj = i.func();
        obj.printf();
    }

    public static void func2(Function<Integer, Class1> f) {
        var obj = f.apply(999);
        obj.printf();
    }
}