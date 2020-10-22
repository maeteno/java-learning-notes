package com.maeteno.study.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Subject {
    @SneakyThrows
    public static void main(String[] args) {
        Observable<Long> observable = Observable.create(
                (ObservableOnSubscribe<Long>) emitter ->
                        Observable.interval(10, TimeUnit.MICROSECONDS, Schedulers.computation())
                                .take(Integer.MAX_VALUE)
                                .subscribe(emitter::onNext)
        ).observeOn(Schedulers.newThread());

        PublishSubject<Long> subject = PublishSubject.create();
        observable.subscribe(subject);

        subject.subscribe(aLong -> log.info("--> Sub1: {}", aLong));
        subject.subscribe(aLong -> log.info("===> Sub2: {}", aLong));

        Thread.sleep(20L);

        subject.subscribe(aLong -> log.info("++++> Sub3: {}", aLong));
        Thread.sleep(100L);
    }
}
