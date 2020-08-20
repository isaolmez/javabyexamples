package com.javabyexamples.aws.dynamodb.maintenance;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemOperations {

    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public void getItem(String tableName, String key) {
        final Map<String, AttributeValue> keyMap = Map.of("firstname", new AttributeValue().withS(key));
        final GetItemRequest getItemRequest = new GetItemRequest().withTableName(tableName)
          .withKey(keyMap);
        final GetItemResult item = amazonDynamoDB.getItem(getItemRequest);
        System.out.println(item.getItem());
    }

    public void getItemWithProjection(String tableName, String key) {
        final Map<String, AttributeValue> keyMap = Map.of("firstname", new AttributeValue().withS(key));
        final GetItemRequest getItemRequest = new GetItemRequest().withTableName(tableName)
          .withKey(keyMap)
          .withProjectionExpression("firstname,lastname");
        final GetItemResult item = amazonDynamoDB.getItem(getItemRequest);
        System.out.println(item.getItem());
    }

    public void putItem(String tableName, String key) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("firstname", new AttributeValue().withS(key));
        item.put("lastname", new AttributeValue().withS("-"));
        final PutItemRequest putItemRequest = new PutItemRequest()
          .withTableName(tableName)
          .withItem(item);
        amazonDynamoDB.putItem(putItemRequest);
    }

    public void updateItem(String tableName, String key) {
        Map<String, AttributeValue> keyMap = new HashMap<>();
        keyMap.put("firstname", new AttributeValue().withS(key));

        final Map<String, AttributeValue> expressionValues = Map.of(
          ":lastname", new AttributeValue().withS("a lastname"),
          ":city", new AttributeValue().withS("a city"));
        final UpdateItemRequest updateItemRequest = new UpdateItemRequest().withTableName(tableName)
          .withKey(keyMap)
          .withUpdateExpression("SET lastname = :lastname, city = :city")
          .withExpressionAttributeValues(expressionValues);
        amazonDynamoDB.updateItem(updateItemRequest);
    }

    public void scan(String tableName) {
        final ScanRequest scanRequest = new ScanRequest(tableName).withConsistentRead(true);
        final ScanResult scan = amazonDynamoDB.scan(scanRequest);
        for (Map<String, AttributeValue> item : scan.getItems()) {
            System.out.println("=>");
            for (Map.Entry<String, AttributeValue> entry : item.entrySet()) {
                System.out.printf("  %s:%s%n", entry.getKey(), entry.getValue().getS());
            }
        }
    }

    public static void main(String[] args) {
        final String tableName = "javabyexamples";
        final ItemOperations itemOperations = new ItemOperations();

        System.out.println("Scan.");
        itemOperations.scan(tableName);

        final String key = UUID.randomUUID().toString();
        System.out.println("New key: " + key);
        itemOperations.putItem(tableName, key);
        itemOperations.updateItem(tableName, key);
        itemOperations.getItem(tableName, key);
        itemOperations.getItemWithProjection(tableName, key);
    }
}
