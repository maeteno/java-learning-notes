package com.maeteno.study.str;

public class InternDemo {

    public static void main(String[] args) {
        String s1 = String.valueOf(11);
        System.out.println(System.identityHashCode(s1));

        String s2 =  s1.intern();
        System.out.println(System.identityHashCode(s2));

        String s3 = "11";
        System.out.println(System.identityHashCode(s3));

        System.out.println(s1 == s3);
        System.out.println(s2 == s3);
    }
}
