package com.javabyexamples.aws.dynamodb.expressions;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UpdateExpressionOperations {

    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public void putItem(String tableName, String key) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("firstname", new AttributeValue().withS(key));
        item.put("surname", new AttributeValue().withS("-"));
        final PutItemRequest putItemRequest = new PutItemRequest()
          .withTableName(tableName)
          .withItem(item);
        amazonDynamoDB.putItem(putItemRequest);
    }

    public void getItem(String tableName, String key) {
        final Map<String, AttributeValue> keyMap = Map.of("firstname", new AttributeValue().withS(key));
        final GetItemRequest getItemRequest = new GetItemRequest().withTableName(tableName)
          .withKey(keyMap);
        final GetItemResult item = amazonDynamoDB.getItem(getItemRequest);
        System.out.println(item.getItem());
    }

    public void updateWithAdd(String tableName, String key) {
        final Map<String, AttributeValue> keyMap = Map.of("firstname", new AttributeValue().withS(key));
        final UpdateItemRequest updateItemRequest = new UpdateItemRequest().withTableName(tableName)
          .withKey(keyMap)
          .withUpdateExpression("ADD view_count :increment")
          .withExpressionAttributeValues(Map.of(":increment", new AttributeValue().withN("1")));
        amazonDynamoDB.updateItem(updateItemRequest);
    }

    public void updateWithSet(String tableName, String key) {
        final Map<String, AttributeValue> keyMap = Map.of("firstname", new AttributeValue().withS(key));
        final UpdateItemRequest updateItemRequest = new UpdateItemRequest().withTableName(tableName)
          .withKey(keyMap)
          .withUpdateExpression("SET view_count = :increment")
          .withExpressionAttributeValues(Map.of(":increment", new AttributeValue().withN("0")));
        amazonDynamoDB.updateItem(updateItemRequest);
    }

    public void updateWithRemove(String tableName, String key) {
        final Map<String, AttributeValue> keyMap = Map.of("firstname", new AttributeValue().withS(key));
        final UpdateItemRequest updateItemRequest = new UpdateItemRequest().withTableName(tableName)
          .withKey(keyMap)
          .withUpdateExpression("REMOVE view_count");
        amazonDynamoDB.updateItem(updateItemRequest);
    }

    public static void main(String[] args) {
        final String tableName = "javabyexamples";
        final UpdateExpressionOperations updateExpressionOperations = new UpdateExpressionOperations();
        final String key = UUID.randomUUID().toString();
        System.out.println("Key: " + key);

        System.out.println("Update item with ADD.");
        updateExpressionOperations.updateWithAdd(tableName, key);
        updateExpressionOperations.getItem(tableName, key);

        System.out.println("Update item with SET.");
        updateExpressionOperations.updateWithSet(tableName, key);
        updateExpressionOperations.getItem(tableName, key);

        System.out.println("Update item with REMOVE.");
        updateExpressionOperations.updateWithRemove(tableName, key);
        updateExpressionOperations.getItem(tableName, key);
    }
}
