package com.javabyexamples.aws.dynamodb.counter;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.javabyexamples.aws.dynamodb.CommonOperations;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LowLevelCounterRepository implements CounterRepository {

    private static final String TABLE_NAME = "counter";

    private final AmazonDynamoDB amazonDynamoDB;

    public LowLevelCounterRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    public void increment(String hashKey) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("key", new AttributeValue().withS(hashKey));

        final UpdateItemRequest updateRequest =
          new UpdateItemRequest()
            .withTableName(TABLE_NAME)
            .withKey(key)
            .withUpdateExpression("ADD #count :incr")
            .withExpressionAttributeNames(Collections.singletonMap("#count", "count"))
            .withExpressionAttributeValues(
              Collections.singletonMap(":incr", new AttributeValue().withN("1")))
            .withReturnValues(ReturnValue.UPDATED_NEW);
        final UpdateItemResult result = amazonDynamoDB.updateItem(updateRequest);

        final Integer count = Optional.ofNullable(result.getAttributes().get("count"))
          .map(AttributeValue::getN)
          .map(Integer::parseInt)
          .orElse(0);
        System.out.println(count);
    }

    public int incrementAlternative(String id) {
        final Map<String, AttributeValue> key = new HashMap<>();
        key.put("key", new AttributeValue().withS(id));

        final UpdateItemRequest updateRequest = new UpdateItemRequest()
          .withTableName(TABLE_NAME)
          .withKey(key)
          .addAttributeUpdatesEntry("count", new AttributeValueUpdate()
            .withValue(new AttributeValue().withN("1"))
            .withAction(AttributeAction.ADD))
          .withReturnValues(ReturnValue.UPDATED_NEW);

        final UpdateItemResult result = amazonDynamoDB.updateItem(updateRequest);

        return Optional.ofNullable(result.getAttributes().get("count"))
          .map(AttributeValue::getN)
          .map(Integer::parseInt)
          .orElse(0);
    }

    @Override
    public void deleteItem(String hashKey) {
        final Map<String, AttributeValue> key = new HashMap<>();
        key.put("key", new AttributeValue().withS(hashKey));

        final DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
          .withKey(key)
          .withReturnValues(ReturnValue.ALL_OLD);

        final DeleteItemResult deleteItemResult = amazonDynamoDB.deleteItem(deleteItemRequest);

        printResponse(deleteItemResult.getSdkResponseMetadata());
    }

    private void printResponse(ResponseMetadata responseMetadata) {
        System.out.println(responseMetadata.toString());
    }

    public static void main(String[] args) throws IOException {
        final AmazonDynamoDB amazonDynamoDB = CommonOperations.getAmazonDynamoDB();
        final LowLevelCounterRepository repository = new LowLevelCounterRepository(amazonDynamoDB);
        repository.increment("key1");
        repository.increment("key1");
        repository.increment("key1");
//    repository.incrementAlternative("key1");
    }
}
