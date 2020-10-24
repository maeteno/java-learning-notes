package com.maeteno.study.rx;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlowableDemo {

    @SneakyThrows
    public static void main(String[] args) {
        log.info("Flowable Demo");

        ParallelFlowable<Integer> parallelFlowable = Flowable.range(1, 100).parallel();

        Disposable subscribe = parallelFlowable
                .runOn(Schedulers.computation())
                .map(i -> {
                    Thread t2 = Thread.currentThread();
                    Thread.sleep(5L);
                    log.info("map ==> {}:{} Value: {}", t2.getName(), t2.getId(), i);
                    return i;
                })
                .sequential()
                .subscribe(i -> {
                    Thread t2 = Thread.currentThread();
                    Thread.sleep(5L);
                    log.info("subscribe ==> {}:{} Value: {}", t2.getName(), t2.getId(), i);
                });

        log.info("{}", subscribe);

        Thread.sleep(2000L);
    }
}
