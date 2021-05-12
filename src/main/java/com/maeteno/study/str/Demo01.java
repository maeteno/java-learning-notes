package com.maeteno.study.str;

public class Demo01 {

    public static void main(String[] args) {
        String s = new String("1");
        System.out.println(System.identityHashCode(s));
        String s5 = s.intern();
        System.out.println(System.identityHashCode(s));
        System.out.println(System.identityHashCode(s5));
        String s2 = "1";
        System.out.println(System.identityHashCode(s2));
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        System.out.println(System.identityHashCode(s3));
        String s6 =  s3.intern();
        System.out.println(System.identityHashCode(s6));
        String s4 = "11";
        System.out.println(System.identityHashCode(s4));
        System.out.println(s3 == s4);
    }
}
