package com.maeteno.study.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWord {

    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) {
                emitter.onNext("Hello Word");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info(s);
            }
        });

        Observable.just("Hello Word").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info(s);
            }
        });

        Observable.just("Hello Word").subscribe(log::info);
    }
}
