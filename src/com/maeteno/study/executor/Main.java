package com.maeteno.study.executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(0, 9089767).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalcuator(numbers);
        Long sum = new ForkJoinPool().invoke(task);
        System.out.println(sum);
    }
}
