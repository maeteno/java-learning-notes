package com.maeteno.study.rx;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class DemoTest {

    @Test
    public void demo1() {
        Flowable.just("Hello world").subscribe(System.out::println);

        Assert.assertTrue(true);
    }

    @Test
    public void demo2() {
        Flowable<Integer> flow = Flowable.range(1, 50)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0);

        flow.subscribe(System.out::println);

        Assert.assertTrue(true);
    }

    @Test
    public void demo3() {
        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                if (time % 2 != 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond!"));
                    break;
                }
            }
        }).subscribe(System.out::println, Throwable::printStackTrace);

        Assert.assertTrue(true);
    }

    @Test
    public void demo4() throws InterruptedException {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000); // <--- wait for the flow to finish

        Assert.assertTrue(true);
    }

    @Test
    public void demo5() throws InterruptedException {
        Flowable<String> source = Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        });

        Flowable<String> runBackground = source.subscribeOn(Schedulers.io());

        Flowable<String> showForeground = runBackground.observeOn(Schedulers.single());

        showForeground.subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000);
    }
}