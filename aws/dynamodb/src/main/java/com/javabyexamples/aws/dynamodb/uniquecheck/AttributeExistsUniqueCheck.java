package com.javabyexamples.aws.dynamodb.uniquecheck;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.javabyexamples.aws.dynamodb.CommonOperations;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AttributeExistsUniqueCheck implements UniqueCheck {

    private final AmazonDynamoDB amazonDynamoDB;

    public AttributeExistsUniqueCheck(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    @Override
    public boolean isUnique(String hashKey) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("key", new AttributeValue().withS(hashKey));

        final UpdateItemRequest incrementRequest = new UpdateItemRequest()
          .withTableName("frontier")
          .withKey(key)
          .withConditionExpression("attribute_not_exists(#seen)")
          .withUpdateExpression("SET #seen= :val")
          .withExpressionAttributeNames(Collections.singletonMap("#seen", "seen"))
          .withExpressionAttributeValues(
            Collections.singletonMap(":val", new AttributeValue().withBOOL(Boolean.TRUE)))
          .withReturnValues(ReturnValue.UPDATED_NEW);

        try {
            amazonDynamoDB.updateItem(incrementRequest);
            return true;
        } catch (ConditionalCheckFailedException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        final AmazonDynamoDB amazonDynamoDB = CommonOperations.getAmazonDynamoDB();
        final AttributeExistsUniqueCheck uniqueCheck = new AttributeExistsUniqueCheck(amazonDynamoDB);
        final String key = "key2";
        System.out.println(uniqueCheck.isUnique(key));
        System.out.println(uniqueCheck.isUnique(key));
        System.out.println(uniqueCheck.isUnique(key));
    }
}
