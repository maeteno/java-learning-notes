package com.maeteno.study.rx;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo {
    @Slf4j
    @Builder
    public static class Obs implements Observer<String> {
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            log.info("Obs onSubscribe");
        }

        @Override
        public void onNext(@NonNull String s) {
            log.info("Obs " + s);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            log.info("Obs " + e.getMessage());
        }

        @Override
        public void onComplete() {
            log.info("Obs onComplete");
        }
    }

    public static void main(String[] args) {
        Disposable subscribe = Observable.just("Hello Word").subscribe(
                log::info,
                (t) -> log.error(t.getMessage()),
                () -> log.info("onComplete")
        );

        Observable.just("Hello Word").subscribe(Obs.builder().build());
    }
}
