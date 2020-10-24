package com.maeteno.study.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchedulerDemo {

    @SneakyThrows
    public static void main(String[] args) {
        Disposable subscribe = Observable.create((ObservableOnSubscribe<Long>) emitter -> {
            Long index = 0L;
            while (index++ < 100 && !emitter.isDisposed()) {
                Thread t = Thread.currentThread();
                log.info("observable ==> {}:{} : {}", t.getName(), t.getId(), index);
                Thread.sleep(5L);
                emitter.onNext(index);
            }
        })
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.single())
                .flatMap((Function<Long, ObservableSource<?>>) aLong -> {
                    Thread t = Thread.currentThread();
                    log.info("flatMap ==> {}:{} value : {}", t.getName(), t.getId(), aLong);
                    Thread.sleep(10L);
                    return Observable.just(aLong).observeOn(Schedulers.io()).map(i -> {
                        Thread t2 = Thread.currentThread();
                        Thread.sleep(5L);
                        log.info("flatMap -> map ==> {}:{} value : {}", t2.getName(), t2.getId(), aLong);
                        return i;
                    });
                })
                .map(i -> {
                    Thread t = Thread.currentThread();
                    log.info("map2 ==> {}:{} value : {}", t.getName(), t.getId(), i);
                    Thread.sleep(5L);
                    return i;
                })
                .subscribe(i -> {
                    Thread t = Thread.currentThread();
                    log.info("subscribe ==> {}:{} value : {}", t.getName(), t.getId(), i);
                });

        log.info("{}", subscribe.isDisposed());
        Thread.sleep(20000L);
    }
}
