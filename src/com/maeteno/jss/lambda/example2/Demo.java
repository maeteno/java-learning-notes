package com.maeteno.jss.lambda.example2;

public class Demo {

    public static void main(String[] args) {
        Class1 l1 = new Class1();

        func((String s) -> {
            System.out.println("Lambda -" + s);
            return "";
        });

        // 实例的成员方方法引用
        func(l1::func);
        // 变量引用实例方法
        Lambda1I lf = l1::func;
        func(lf);

        // 类静态方法的引用
        func(Class1::funcStatic);
        // 变量引用静态方法
        Lambda1I cf = Class1::funcStatic;
        func(cf);

        // 类实例的成员方法引用
        func2((Class1 c, Integer i) -> c.func2(i));
        func2(Class1::func2);
    }

    public static void func(Lambda1I l) {
        l.func("demo");
    }

    public static void func2(Lambda2I l) {
        l.func(new Class1(), 1);
    }
}