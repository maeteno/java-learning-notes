package com.maeteno.study.flow;

import java.util.concurrent.Flow;

public class Main {
    public static void main(String[] args) {
        getTemperatures("Shang Hai").subscribe(new TempSubscriber());
    }

    private static Flow.Publisher<TempInfo> getTemperatures(String town) {

        //  subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
        return new Flow.Publisher<TempInfo>() {
            @Override
            public void subscribe(Flow.Subscriber<? super TempInfo> subscriber) {
                subscriber.onSubscribe(new TempSubscription(subscriber, town));
            }
        };
    }
}
