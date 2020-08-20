package com.javabyexamples.aws.dynamodb.indexes;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateGlobalSecondaryIndexAction;
import com.amazonaws.services.dynamodbv2.model.DeleteGlobalSecondaryIndexAction;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.GlobalSecondaryIndexUpdate;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.UpdateGlobalSecondaryIndexAction;
import com.amazonaws.services.dynamodbv2.model.UpdateTableRequest;

public class GlobalSecondaryIndexOperations {

    private final AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

    public void createIndex(String tableName, String indexName) {
        final CreateGlobalSecondaryIndexAction secondaryIndexAction = new CreateGlobalSecondaryIndexAction()
          .withIndexName(indexName)
          .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(5L).withWriteCapacityUnits(1L))
          .withKeySchema(
            new KeySchemaElement("age", KeyType.HASH),
            new KeySchemaElement("firstname", KeyType.RANGE))
          .withProjection(new Projection().withProjectionType(ProjectionType.INCLUDE).withNonKeyAttributes("city"));
        final GlobalSecondaryIndexUpdate globalSecondaryIndexUpdate = new GlobalSecondaryIndexUpdate()
          .withCreate(secondaryIndexAction);
        final UpdateTableRequest updateTableRequest = new UpdateTableRequest()
          .withTableName(tableName)
          .withAttributeDefinitions(
            new AttributeDefinition().withAttributeName("age").withAttributeType(ScalarAttributeType.S),
            new AttributeDefinition().withAttributeName("firstname").withAttributeType(ScalarAttributeType.S))
          .withGlobalSecondaryIndexUpdates(globalSecondaryIndexUpdate);

        amazonDynamoDB.updateTable(updateTableRequest);
    }

    public void updateIndexForProvisionedThroughput(String tableName, String indexName) {
        final UpdateGlobalSecondaryIndexAction secondaryIndexAction = new UpdateGlobalSecondaryIndexAction()
          .withIndexName(indexName)
          .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(5L).withWriteCapacityUnits(2L));
        final GlobalSecondaryIndexUpdate globalSecondaryIndexUpdate = new GlobalSecondaryIndexUpdate()
          .withUpdate(secondaryIndexAction);
        final UpdateTableRequest updateTableRequest = new UpdateTableRequest()
          .withTableName(tableName)
          .withGlobalSecondaryIndexUpdates(globalSecondaryIndexUpdate);

        amazonDynamoDB.updateTable(updateTableRequest);
    }

    public void deleteIndex(String tableName, String indexName) {
        final DeleteGlobalSecondaryIndexAction deleteGlobalSecondaryIndexAction = new DeleteGlobalSecondaryIndexAction()
          .withIndexName(indexName);
        final GlobalSecondaryIndexUpdate globalSecondaryIndexUpdate = new GlobalSecondaryIndexUpdate()
          .withDelete(deleteGlobalSecondaryIndexAction);
        final UpdateTableRequest updateTableRequest = new UpdateTableRequest()
          .withTableName(tableName)
          .withGlobalSecondaryIndexUpdates(globalSecondaryIndexUpdate);

        amazonDynamoDB.updateTable(updateTableRequest);
    }


    public void describeTable(String tableName) {
        final DescribeTableResult describeTableResult = amazonDynamoDB
          .describeTable(new DescribeTableRequest(tableName));

        System.out.println(describeTableResult.getTable().getGlobalSecondaryIndexes());
    }

    public static void main(String[] args) {
        final String tableName = "javabyexamples";
        final String indexName = "globalSecondary1";
        final GlobalSecondaryIndexOperations indexOperations = new GlobalSecondaryIndexOperations();
        System.out.println("Create global secondary index.");
        indexOperations.createIndex(tableName, indexName);

        System.out.println("Update global secondary index.");
        indexOperations.updateIndexForProvisionedThroughput(tableName, indexName);

        System.out.println("Delete global secondary index.");
        indexOperations.deleteIndex(tableName, indexName);

        indexOperations.describeTable(tableName);
    }
}
