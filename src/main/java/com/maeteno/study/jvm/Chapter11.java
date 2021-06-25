package com.maeteno.study.jvm;

public class Chapter11 {
    public int test(String name) {
        switch (name) {
            case "Java":
                return 100;
            case "Kotlin":
                return 200;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println("---");
    }
}
