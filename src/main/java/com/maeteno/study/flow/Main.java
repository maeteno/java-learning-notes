package com.maeteno.study.flow;

import java.util.concurrent.Flow;

public class Main {
    public static void main(String[] args) {
        getTemperatures("Shang Hai").subscribe(new TempSubscriber());
    }

    private static Flow.Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }
}
