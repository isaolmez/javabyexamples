package com.javabyexamples.spring.mvc1.currentrequest.threadlocal;

import com.javabyexamples.spring.mvc1.currentrequest.Person;

public class ThreadLocalRequestContext {

    private static final InheritableThreadLocal<Person> REQUEST_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void set(Person person) {
        REQUEST_THREAD_LOCAL.set(person);
    }

    public static Person get() {
        return REQUEST_THREAD_LOCAL.get();
    }

    public static void clear() {
        REQUEST_THREAD_LOCAL.remove();
    }
}
