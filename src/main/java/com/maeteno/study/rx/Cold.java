package com.maeteno.study.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Cold {
    @SneakyThrows
    public static void main(String[] args) {
        Observable<Long> observable = Observable.create(
                (ObservableOnSubscribe<Long>) emitter -> Observable.interval(10, TimeUnit.MICROSECONDS, Schedulers.computation()).take(Integer.MAX_VALUE
                ).subscribe(emitter::onNext)).observeOn(Schedulers.newThread());

        observable.subscribe(aLong -> log.info("--> Sub1: {}", aLong));
        observable.subscribe(aLong -> log.info("===> Sub2: {}", aLong));
        Thread.sleep(20L);

        observable.subscribe(aLong -> log.info("++++> Sub3: {}", aLong));
        Thread.sleep(100L);
    }
}
