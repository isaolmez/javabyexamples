package com.javabyexamples.spring.core.scopeddependency.beanlookupannotation;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class ClientService {

    public void doWork() {
        Timer timer = getTimer();
        timer.start();
        timer.stop();
    }

    public void doWorkWithName() {
        Timer timer = getTimerWithName();
        timer.start();
        timer.stop();
    }

    @Lookup
    protected abstract Timer getTimer();

    @Lookup(value = "timer")
    protected abstract Timer getTimerWithName();
}
