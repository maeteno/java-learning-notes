package com.maeteno.study.str;

public class Demo03 {
    public static void main(String[] args) {
        String s = new String("1234");
        s.intern();
        String s2 = "1234";
        System.out.println(s == s2);
    }
}
