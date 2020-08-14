package com.javabyexamples.aws.dynamodb.counter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "counter")
public class Counter {

    @DynamoDBHashKey
    private String key;

    @DynamoDBAttribute
    private int count;
}
