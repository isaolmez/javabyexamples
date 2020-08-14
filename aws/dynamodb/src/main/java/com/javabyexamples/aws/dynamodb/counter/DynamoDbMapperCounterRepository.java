package com.javabyexamples.aws.dynamodb.counter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamoDbMapperCounterRepository implements CounterRepository {

    @Override
    public void increment(String hashKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteItem(String hashKey) {
        throw new UnsupportedOperationException();
    }
}
