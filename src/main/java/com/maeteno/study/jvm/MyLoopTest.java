package com.maeteno.study.jvm;

public class MyLoopTest {
    public static int[] numbers = new int[]{1, 2, 3};

    public static void main(String[] args) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }

        System.out.println(sum);
    }
}
