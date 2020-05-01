package com.javabyexamples.spring.core.scopeddependency.beanlookup;

public abstract class ClientService {

    public void doWork() {
        Timer timer = getTimer();
        timer.start();
        timer.stop();
    }

    protected abstract Timer getTimer();
}
