package com.maeteno.study.rx;

import io.reactivex.Flowable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class FlowableDemo2 {

    @SneakyThrows
    public static void main(String[] args) {
        log.info("Flowable Demo2");

        ParallelFlowable<Integer> parallelFlowable = Flowable.range(1, 200).parallel();
        Subscriber<Integer>[] subscribers = subscribers();

        parallelFlowable
                .runOn(Schedulers.io())
                .map(i -> {
                    Thread t2 = Thread.currentThread();
                    log.info("map ==> {}:{} Value: {}", t2.getName(), t2.getId(), i);
                    return i;
                })
                .subscribe(subscribers);

        Thread.sleep(200000L);
    }

    private static Subscriber<Integer>[] subscribers() {
        return new MySubscriber[]{
                new MySubscriber(),
                new MySubscriber(),
                new MySubscriber(),
                new MySubscriber(),
                new MySubscriber(),
                new MySubscriber(),
                new MySubscriber(),
                new MySubscriber(),
                new MySubscriber(),
                new MySubscriber(),
                new MySubscriber(),
        };
    }

    public static class MySubscriber implements Subscriber<Integer> {

        @SneakyThrows
        @Override
        public void onSubscribe(Subscription subscription) {
            Thread t2 = Thread.currentThread();
            Thread.sleep(5L);
            log.info("onSubscribe ==> {}:{}", t2.getName(), t2.getId());
        }

        @SneakyThrows
        @Override
        public void onNext(Integer i) {
            Thread t2 = Thread.currentThread();
            Thread.sleep(5L);
            log.info("onNext ==> {}:{} Value: {}", t2.getName(), t2.getId(), i);
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
    }
}
