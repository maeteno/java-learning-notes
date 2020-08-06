package com.maeteno.jss.lambda.example1;

public class Demo {

    public static void main(String[] args) {
        func(new DemoImpl());

        func(new DemoI() {
            public String func() {
                return "Anonymous class";
            }
        });

        func(() -> "Lambda");
        DemoI lamVar = () -> "Lambda Var";
        func(lamVar);

        func(DemoImpl::funcs);
    }

    public static void func(DemoI demo) {
        var s = demo.func();
        System.out.println(s);
    }
}