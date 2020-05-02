package com.javabyexamples.java.test.wiremock.server;

import com.github.tomakehurst.wiremock.common.Notifier;

public class NoOpNotifier implements Notifier {

    @Override
    public void info(String message) {
    }

    @Override
    public void error(String message) {
    }

    @Override
    public void error(String message, Throwable t) {
    }
}
