package com.maeteno.study.jvm;

public class InternDemo {

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str3 = "java";
        System.out.println(str3.intern() == "java");

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

        String str4 = new String("java");
        System.out.println(str4.intern() == str3);
    }
}
