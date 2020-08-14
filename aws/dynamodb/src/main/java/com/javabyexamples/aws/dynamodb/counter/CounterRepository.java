package com.javabyexamples.aws.dynamodb.counter;

public interface CounterRepository {

    void increment(String hashKey);

    void deleteItem(String hashKey);
}
