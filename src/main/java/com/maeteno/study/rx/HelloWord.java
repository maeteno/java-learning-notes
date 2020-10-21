package com.maeteno.study.rx;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWord {

    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("Hello Word");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                log.info(s);
            }
        });

        Observable.just("Hello Word").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                log.info(s);
            }
        });

        Observable.just("Hello Word").subscribe(log::info);
    }
}
