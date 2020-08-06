package com.maeteno.jss.lambda.example2;

public class Class1 {
    public String func(String s) {
        System.out.println(s + " Impl");
        return s + "1234";
    }

     public static String funcStatic(String s) {
        System.out.println(s + "Static Impl");
        return s + "1234";
    }

    public void func2(Integer i){
        System.out.println(i);
    }
}