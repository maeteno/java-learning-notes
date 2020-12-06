package com.maeteno.study.flow;

import java.util.concurrent.Flow;

public class TempSubscription implements Flow.Subscription {
    private final Flow.Subscriber<? super TempInfo> subscriber;
    private final String town;

    public TempSubscription(Flow.Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    /**
     * Adds the given number {@code n} of items to the current
     * unfulfilled demand for this subscription.  If {@code n} is
     * less than or equal to zero, the Subscriber will receive an
     * {@code onError} signal with an {@link
     * IllegalArgumentException} argument.  Otherwise, the
     * Subscriber will receive up to {@code n} additional {@code
     * onNext} invocations (or fewer if terminated).
     *
     * @param n the increment of demand; a value of {@code
     *          Long.MAX_VALUE} may be considered as effectively unbounded
     */
    @Override
    public void request(long n) {
        for (long i = 0L; i < n; i++) {
            try {
                subscriber.onNext(TempInfo.fetch(town));
            } catch (Exception e) {
                subscriber.onError(e);
                break;
            }
        }
    }

    /**
     * Causes the Subscriber to (eventually) stop receiving
     * messages.  Implementation is best-effort -- additional
     * messages may be received after invoking this method.
     * A cancelled subscription need not ever receive an
     * {@code onComplete} or {@code onError} signal.
     */
    @Override
    public void cancel() {
        subscriber.onComplete();
    }
}
