package com.nextbest.whatandwhen.utils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class RetryWithDelay implements Function<Flowable<? extends Throwable>, Flowable<?>> {

    private final int MAX_RETIRES_COUNT = 3;
    private final int RETIRE_DELAY = 500;


    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
        this.retryCount = 0;
    }

    public RetryWithDelay() {
        this.maxRetries = MAX_RETIRES_COUNT;
        this.retryDelayMillis = RETIRE_DELAY;
        this.retryCount = 0;
    }

    @Override
    public Flowable<?> apply(Flowable<? extends Throwable> flowable) throws Exception {
        return flowable
                .flatMap((Function<Throwable, Flowable<?>>) throwable -> {
                    if (++retryCount < maxRetries && (throwable instanceof SocketTimeoutException || throwable instanceof UnknownHostException)) {
                        return Flowable.timer(retryDelayMillis,
                                TimeUnit.MILLISECONDS);
                    }
                    return Flowable.error(throwable);
                });
    }
}
