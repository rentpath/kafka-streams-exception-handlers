package com.rentpath.kafka.streams.handlers;


import io.sentry.Sentry;

abstract public class SentryExceptionHandler {
    public SentryExceptionHandler() {
        Sentry.init();
    }

    public void capture(Throwable e) {
        Sentry.capture(e);
    }
}
