package com.javabyexamples.aws.dynamodb.query;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QueryOperations {

    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public void query(String tableName, String hashKey) {
        final QueryRequest queryRequest = new QueryRequest(tableName)
          .withKeyConditionExpression("firstname = :firstname")
          .withExpressionAttributeValues(Map.of(":firstname", new AttributeValue().withS(hashKey)));
        final QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        System.out.println(queryResult.getItems());
    }

    public void queryWithPagination(String tableName, String hashKey, Map<String, AttributeValue> startKey) {
        final QueryRequest queryRequest = new QueryRequest(tableName)
          .withKeyConditionExpression("firstname = :firstname")
          .withExpressionAttributeValues(Map.of(":firstname", new AttributeValue().withS(hashKey)))
          .withExclusiveStartKey(startKey)
          .withLimit(2);

        final QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        System.out.println(queryResult.getItems());

        if (queryResult.getLastEvaluatedKey() != null) {
            System.out.println("Fetch new page.");
            queryWithPagination(tableName, hashKey, queryResult.getLastEvaluatedKey());
        }
    }

    public void queryComposite(String tableName, String hashKey) {
        final QueryRequest queryRequest = new QueryRequest(tableName)
          .withKeyConditionExpression("firstname = :firstname and lastname = :lastname")
          .withExpressionAttributeValues(Map.of(
            ":firstname", new AttributeValue().withS(hashKey),
            ":lastname", new AttributeValue().withS("-")))
          .withScanIndexForward(false);
        final QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        System.out.println(queryResult.getItems());
    }

    public void queryByFiltering(String tableName, String hashKey) {
        final QueryRequest queryRequest = new QueryRequest(tableName)
          .withKeyConditionExpression("firstname = :firstname")
          .withFilterExpression("age <> :age")
          .withExpressionAttributeValues(Map.of(
            ":firstname", new AttributeValue().withS(hashKey),
            ":age", new AttributeValue().withN("20")))
          .withScanIndexForward(false);
        final QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        System.out.println(queryResult.getItems());
    }

    public void putItem(String tableName, String key, String other) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("firstname", new AttributeValue().withS(key));
        item.put("lastname", new AttributeValue().withS(other));
        final PutItemRequest putItemRequest = new PutItemRequest()
          .withTableName(tableName)
          .withItem(item);
        amazonDynamoDB.putItem(putItemRequest);
    }

    public static void main(String[] args) {
        final String tableName = "javabyexamples";
        final String compositeTableName = "javabyexamples_composite";
        final String key = UUID.randomUUID().toString();
        final QueryOperations queryOperations = new QueryOperations();
        queryOperations.putItem(tableName, key, "-");
        queryOperations.putItem(compositeTableName, key, UUID.randomUUID().toString());
        queryOperations.putItem(compositeTableName, key, UUID.randomUUID().toString());
        queryOperations.putItem(compositeTableName, key, UUID.randomUUID().toString());
        queryOperations.putItem(compositeTableName, key, UUID.randomUUID().toString());
        queryOperations.putItem(compositeTableName, key, UUID.randomUUID().toString());

        System.out.println("Query.");
        queryOperations.query(tableName, key);

        System.out.println("Query composite.");
        queryOperations.queryComposite(compositeTableName, key);

        System.out.println("Query by filtering.");
        queryOperations.queryByFiltering(tableName, key);

        System.out.println("Query by pagination.");
        queryOperations.queryWithPagination(compositeTableName, key, null);
    }
}
