package com.javabyexamples.aws.dynamodb.maintenance;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.BillingMode;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import java.util.Arrays;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableOperations {

    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public void createTableIfNotExists(String tableName) {
        try {
            log.info("Attempting to create table {}", tableName);

            final CreateTableRequest request = new CreateTableRequest()
              .withTableName(tableName)
              .withKeySchema(Arrays.asList(new KeySchemaElement("key", KeyType.HASH)))
              .withAttributeDefinitions(
                Arrays.asList(new AttributeDefinition("key", ScalarAttributeType.S)))
              .withBillingMode(BillingMode.PAY_PER_REQUEST);
            TableUtils.createTableIfNotExists(amazonDynamoDB, request);
            TableUtils.waitUntilActive(amazonDynamoDB, request.getTableName());

            log.info("Table is created: {}", tableName);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create table: " + tableName, e);
        }
    }

    public static void main(String[] args) {
        final TableOperations tableOperations = new TableOperations();
        tableOperations.createTableIfNotExists(UUID.randomUUID().toString());
    }
}
