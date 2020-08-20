package com.javabyexamples.aws.dynamodb.maintenance;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.BillingMode;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeLimitsRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeLimitsResult;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import java.util.Arrays;
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

    public void listTables() {
        final ListTablesRequest listTablesRequest = new ListTablesRequest()
          .withLimit(3);
        final ListTablesResult listTablesResult = amazonDynamoDB.listTables(listTablesRequest);
        System.out.println("Tables:");
        for (String tableName : listTablesResult.getTableNames()) {
            System.out.println("- " + tableName);

        }
    }

    public void describeTable(String tableName) {
        final DescribeTableResult describeTableResult = amazonDynamoDB
          .describeTable(new DescribeTableRequest(tableName));

        System.out.println(describeTableResult.getTable());
    }

    public void describeLimits(String tableName) {
        final DescribeLimitsResult describeLimitsResult = amazonDynamoDB.describeLimits(new DescribeLimitsRequest());

        System.out.println(describeLimitsResult);
    }

    public static void main(String[] args) {
        final TableOperations tableOperations = new TableOperations();
        final String tableName = "table_operations";
        tableOperations.createTableIfNotExists(tableName);

        System.out.println("List tables.");
        tableOperations.listTables();

        System.out.println("Describe table: " + tableName);
        tableOperations.describeTable(tableName);

        System.out.println("Describe limits.");
        tableOperations.describeLimits(tableName);
    }
}
