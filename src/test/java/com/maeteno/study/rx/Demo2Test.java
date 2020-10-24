package com.maeteno.study.rx;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Assert;
import org.junit.Test;

public class Demo2Test {

    @Test
    public void test1() {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);

        Assert.assertTrue(true);
    }

    @Test
    public void test2() {
        Flowable.range(1, 10)
                .flatMap(v -> Flowable.just(v)
                        .subscribeOn(Schedulers.computation())
                        .map(w -> w * w))
                .blockingSubscribe(System.out::println);

        Assert.assertTrue(true);
    }

    @Test
    public void test3() {
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v * v)
                .sequential()
                .blockingSubscribe(System.out::println);

        Assert.assertTrue(true);
    }
}
