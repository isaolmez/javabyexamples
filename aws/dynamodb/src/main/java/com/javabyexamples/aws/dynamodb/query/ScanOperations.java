package com.javabyexamples.aws.dynamodb.query;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import java.util.Map;

public class ScanOperations {

    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public void scan(String tableName) {
        final ScanRequest scanRequest = new ScanRequest(tableName);
        final ScanResult scan = amazonDynamoDB.scan(scanRequest);
        System.out.println(scan.getItems());
    }

    public void scanWithPagination(String tableName, Map<String, AttributeValue> startKey) {
        final ScanRequest scanRequest = new ScanRequest(tableName)
          .withExclusiveStartKey(startKey)
          .withLimit(2);

        final ScanResult scanResult = amazonDynamoDB.scan(scanRequest);
        System.out.println(scanResult.getItems());

        if (scanResult.getLastEvaluatedKey() != null) {
            System.out.println("Fetch new page.");
            scanWithPagination(tableName, scanResult.getLastEvaluatedKey());
        }
    }

    public void scanByFiltering(String tableName) {
        final ScanRequest scanRequest = new ScanRequest(tableName)
          .withFilterExpression("age <> :age")
          .withExpressionAttributeValues(Map.of(
            ":age", new AttributeValue().withN("20")));
        final ScanResult scanResult = amazonDynamoDB.scan(scanRequest);
        System.out.println(scanResult.getItems());
    }

    public static void main(String[] args) {
        final String tableName = "javabyexamples";
        final ScanOperations queryOperations = new ScanOperations();

        System.out.println("Query.");
        queryOperations.scan(tableName);

        System.out.println("Query by filtering.");
        queryOperations.scanByFiltering(tableName);

        System.out.println("Query by pagination.");
        queryOperations.scanWithPagination(tableName, null);
    }
}
