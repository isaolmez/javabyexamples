package com.javabyexamples.aws.sqs.queue;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QueueOperations {

    private final AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();

    public void listQueues() {
        final ListQueuesResult listQueuesResult = amazonSQS.listQueues();
        for (String queueUrl : listQueuesResult.getQueueUrls()) {
            System.out.println("Queue: " + queueUrl);
        }
    }

    public void deleteQueue(String queueUrl) {
        amazonSQS.deleteQueue(new DeleteQueueRequest(queueUrl));
    }

    public void deleteQueues() {
        final ListQueuesResult listQueuesResult = amazonSQS.listQueues();
        for (String queueUrl : listQueuesResult.getQueueUrls()) {
            amazonSQS.deleteQueue(new DeleteQueueRequest(queueUrl));
        }
    }

    public String createQueue(String queueName) {
        final CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        return amazonSQS.createQueue(createQueueRequest).getQueueUrl();
    }

    public String createQueueOverridingDefaults(String queueName) {
        final CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        Map<String, String> attributes = new HashMap<>();
        attributes.put("DelaySeconds", "10");
        attributes.put("MessageRetentionPeriod", "60");
        attributes.put("ReceiveMessageWaitTimeSeconds", "10");
        attributes.put("VisibilityTimeout", "60");

        createQueueRequest.withAttributes(attributes);
        return amazonSQS.createQueue(createQueueRequest).getQueueUrl();
    }

    public String createFifoQueue(String queueName) {
        final CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        Map<String, String> attributes = new HashMap<>();
        attributes.put("FifoQueue", "true");
        attributes.put("ContentBasedDeduplication", "true");

        createQueueRequest.withAttributes(attributes);
        return amazonSQS.createQueue(createQueueRequest).getQueueUrl();
    }

    public void updateQueue(String queueUrl) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("DelaySeconds", "10");
        attributes.put("MessageRetentionPeriod", "600");
        attributes.put("ReceiveMessageWaitTimeSeconds", "0");
        attributes.put("VisibilityTimeout", "600");
        final SetQueueAttributesRequest createQueueRequest = new SetQueueAttributesRequest(queueUrl, attributes);

        amazonSQS.setQueueAttributes(createQueueRequest);
    }

    public String getQueueUrl(String queueName) {
        final GetQueueUrlRequest getQueueUrlRequest = new GetQueueUrlRequest(queueName);
        final GetQueueUrlResult queueUrl = amazonSQS.getQueueUrl(getQueueUrlRequest);

        return queueUrl.getQueueUrl();
    }

    public static void main(String[] args) {
        final QueueOperations queueOperations = new QueueOperations();
        System.out.println("Create queue.");
        final String queueName = UUID.randomUUID().toString();
        final String queueUrl = queueOperations.createQueue(queueName);
        System.out.println("Create FIFO queue.");
        queueOperations.createFifoQueue(String.format("%s.fifo", UUID.randomUUID().toString()));
        System.out.println("Update queue.");
        queueOperations.updateQueue(queueUrl);
        System.out.println("Get queue URL by name.");
        final String fetchedQueueUrl = queueOperations.getQueueUrl(queueName);
        System.out.println("Fetched queue URL: " + fetchedQueueUrl);
//        System.out.println("List queues.");
//        queueOperations.listQueues();
//        System.out.println("Delete all queues.");
//        queueOperations.deleteQueues();
    }
}
