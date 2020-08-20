package com.javabyexamples.aws.dynamodb.streams;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ListStreamsRequest;
import com.amazonaws.services.dynamodbv2.model.ListStreamsResult;
import com.amazonaws.services.dynamodbv2.model.Stream;

public class StreamOperations {

    private final AmazonDynamoDBStreams dynamoDBStreams = AmazonDynamoDBStreamsClientBuilder.defaultClient();

    public void listStreams(String tableName) {
        final ListStreamsRequest listStreamsRequest = new ListStreamsRequest().withTableName(tableName);
        final ListStreamsResult listStreamsResult = dynamoDBStreams.listStreams(listStreamsRequest);
        for (Stream stream : listStreamsResult.getStreams()) {
            System.out.printf("- %s:%s", stream.getStreamArn(), stream.getStreamLabel());
        }
    }

    public static void main(String[] args) {
        final StreamOperations streamOperations = new StreamOperations();
        streamOperations.listStreams("certtable");
    }
}
