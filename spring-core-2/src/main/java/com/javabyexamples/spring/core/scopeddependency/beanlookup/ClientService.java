package com.javabyexamples.spring.core.scopeddependency.beanlookup;

public abstract class ClientService {

    public void doWork() {
        Counter counter = getCounter();
        System.out.println(counter.count());
    }

    protected abstract Counter getCounter();
}
