package com.javabyexamples.aws.dynamodb.ttl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.DescribeTimeToLiveRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTimeToLiveResult;

public class TtlOperations {

    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public void getTtl(String tableName) {
        final DescribeTimeToLiveResult describeTimeToLiveResult = amazonDynamoDB
          .describeTimeToLive(new DescribeTimeToLiveRequest().withTableName(tableName));
        System.out.println(describeTimeToLiveResult.getTimeToLiveDescription());
    }

    public static void main(String[] args) {
        final String tableName = "javabyexamples";
        final TtlOperations ttlOperations = new TtlOperations();
        ttlOperations.getTtl(tableName);
    }
}
