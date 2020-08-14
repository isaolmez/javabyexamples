package com.javabyexamples.aws.dynamodb;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonOperations {

    public static AmazonDynamoDB getAmazonDynamoDB() {
        return AmazonDynamoDBAsyncClientBuilder
          .standard()
          .withRegion(Regions.US_EAST_1)
          .build();
    }
}
