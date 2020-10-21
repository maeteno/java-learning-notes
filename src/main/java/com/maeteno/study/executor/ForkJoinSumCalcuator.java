package com.maeteno.study.executor;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalcuator extends RecursiveTask<Long> {

    public static final long THRESHOLD = 1_000;
    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalcuator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalcuator(long[] numbers, int statrt, int end) {
        this.numbers = numbers;
        this.start = statrt;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;

        if (length <= THRESHOLD) {
            return computeSequentially();
        }

        ForkJoinSumCalcuator leftTask = new ForkJoinSumCalcuator(numbers, start, start + length / 2);
        leftTask.fork(); // 异步执行第一个任务

        ForkJoinSumCalcuator rightTask = new ForkJoinSumCalcuator(numbers, start + length / 2, end);

        Long rightResult = rightTask.compute(); // 同步执行第二个任务
        Long leftResult = leftTask.join(); // 获取第一个任务的结果，如果没有完成就阻塞等待

        return leftResult + rightResult;

    }

    private long computeSequentially() {
        long sum = 0;

        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

}
