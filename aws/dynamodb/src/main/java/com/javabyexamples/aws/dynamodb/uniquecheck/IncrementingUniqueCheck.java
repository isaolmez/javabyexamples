package com.javabyexamples.aws.dynamodb.uniquecheck;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.javabyexamples.aws.dynamodb.CommonOperations;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IncrementingUniqueCheck implements UniqueCheck {

    private final AmazonDynamoDB amazonDynamoDB;

    public IncrementingUniqueCheck(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    @Override
    public boolean isUnique(String hashKey) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("key", new AttributeValue().withS(hashKey));

        final UpdateItemRequest incrementRequest = new UpdateItemRequest()
          .withTableName("counter")
          .withKey(key)
          .withUpdateExpression("ADD #count :incr")
          .withExpressionAttributeNames(Collections.singletonMap("#count", "count"))
          .withExpressionAttributeValues(
            Collections.singletonMap(":incr", new AttributeValue().withN("1")))
          .withReturnValues(ReturnValue.UPDATED_NEW);

        final UpdateItemResult result = amazonDynamoDB.updateItem(incrementRequest);

        final Integer count = Optional.ofNullable(result.getAttributes().get("count"))
          .map(AttributeValue::getN)
          .map(Integer::parseInt)
          .orElse(0);

        return count == 1;
    }

    public static void main(String[] args) {
        final AmazonDynamoDB amazonDynamoDB = CommonOperations.getAmazonDynamoDB();
        final IncrementingUniqueCheck uniqueCheck = new IncrementingUniqueCheck(amazonDynamoDB);
        final String key = "key2";
        System.out.println(uniqueCheck.isUnique(key));
        System.out.println(uniqueCheck.isUnique(key));
        System.out.println(uniqueCheck.isUnique(key));
    }
}
