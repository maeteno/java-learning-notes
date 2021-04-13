package com.maeteno.study.str;

public class StrEquals {

    public static void main(String[] args) {
        String s1 = "Demo";
        String s2 = new String("Demo");

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1.compareTo(s2));
    }
}
