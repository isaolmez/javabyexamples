package com.javabyexamples.spring.core.scopeddependency.beanlookupannotation;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class ClientService {

    public void doWork() {
        System.out.println("Processing...");
        Counter counter = getCounter();
        System.out.println(counter.count());
    }

    public void doWorkWithQualifier() {
        System.out.println("Processing with qualifier...");
        Counter counter = getCounterWithQualifier();
        System.out.println(counter.count());
    }

    @Lookup
    protected abstract Counter getCounter();

    @Lookup(value = "counter")
    protected abstract Counter getCounterWithQualifier();
}
