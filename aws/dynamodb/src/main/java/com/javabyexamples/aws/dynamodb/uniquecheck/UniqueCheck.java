package com.javabyexamples.aws.dynamodb.uniquecheck;

public interface UniqueCheck {

    boolean isUnique(String hashKey);
}
