package com.javabyexamples.aws.dynamodb.expressions;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import java.util.Map;
import java.util.UUID;

public class ConditionExpressionOperations {

    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public void getItem(String tableName, String key) {
        final Map<String, AttributeValue> keyMap = Map.of("firstname", new AttributeValue().withS(key));
        final GetItemRequest getItemRequest = new GetItemRequest().withTableName(tableName)
          .withKey(keyMap);
        final GetItemResult item = amazonDynamoDB.getItem(getItemRequest);
        System.out.println(item.getItem());
    }

    public void updateConditionally(String tableName, String key) {
        final Map<String, AttributeValue> keyMap = Map.of("firstname", new AttributeValue().withS(key));
        final UpdateItemRequest updateItemRequest = new UpdateItemRequest().withTableName(tableName)
          .withKey(keyMap)
          .withConditionExpression("attribute_exists(surname)")
          .withUpdateExpression("SET surname = :surname")
          .withExpressionAttributeValues(Map.of(":surname", new AttributeValue().withS("updated")));
        amazonDynamoDB.updateItem(updateItemRequest);
    }

    public void putConditionally(String tableName, String key) {
        final Map<String, AttributeValue> item = Map.of("firstname", new AttributeValue().withS(key),
          "surname", new AttributeValue().withS("a surname"));
        final PutItemRequest putItemRequest = new PutItemRequest().withTableName(tableName)
          .withItem(item)
          .withConditionExpression("attribute_not_exists(firstname)");
        try {
            amazonDynamoDB.putItem(putItemRequest);
        } catch (ConditionalCheckFailedException e) {
            System.out.println("Condition failed.");
        }
    }

    public void deleteConditionally(String tableName, String key) {
        final Map<String, AttributeValue> keyMap = Map.of("firstname", new AttributeValue().withS(key));
        final DeleteItemRequest deleteItemRequest = new DeleteItemRequest().withTableName(tableName)
          .withKey(keyMap)
          .withConditionExpression("attribute_exists(surname)");
        try {
            amazonDynamoDB.deleteItem(deleteItemRequest);
        } catch (ConditionalCheckFailedException e) {
            System.out.println("Condition failed.");
        }
    }

    public static void main(String[] args) {
        final String tableName = "javabyexamples";
        final ConditionExpressionOperations expressionOperations = new ConditionExpressionOperations();

        final String key = UUID.randomUUID().toString();
        System.out.println("Put item conditionally: " + key);
        expressionOperations.putConditionally(tableName, key);
        System.out.println("Put item conditionally again: " + key);
        expressionOperations.putConditionally(tableName, key);

        System.out.println("Update item conditionally.");
        expressionOperations.updateConditionally(tableName, key);

        System.out.println("Get item.");
        expressionOperations.getItem(tableName, key);

        System.out.println("Put item conditionally: " + key);
        expressionOperations.putConditionally(tableName, key);

        System.out.println("Delete item conditionally: " + key);
        expressionOperations.deleteConditionally(tableName, key);

        System.out.println("Get item.");
        expressionOperations.getItem(tableName, key);
    }
}
