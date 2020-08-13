package com.javabyexamples.aws.sqs.longpolling;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LongPolling {

    private final AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();

    public void sendAndReceive() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CreateQueueRequest createQueueRequest = new CreateQueueRequest(UUID.randomUUID().toString());
        String queueUrl = amazonSQS.createQueue(createQueueRequest).getQueueUrl();

        executorService.submit(() -> {
            ReceiveMessageResult receiveMessageResult = longPoll(queueUrl);
            System.out.println("Message result: " + receiveMessageResult.getMessages());
        });

        SendMessageRequest sendRequest = new SendMessageRequest()
          .withQueueUrl(queueUrl)
          .withMessageBody("Hello world!");
        SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendRequest);
        System.out.printf("Message has been sent with %s:%n", sendMessageResult.getMessageId());

        executorService.shutdown();
    }

    private ReceiveMessageResult longPoll(String queueUrl) {
        ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest()
          .withQueueUrl(queueUrl)
          .withWaitTimeSeconds(10);
        return amazonSQS.receiveMessage(receiveRequest);
    }

    private ReceiveMessageResult shortPoll(String queueUrl) {
        ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest()
          .withQueueUrl(queueUrl);
        return amazonSQS.receiveMessage(receiveRequest);
    }

    public static void main(String[] args) {
        final LongPolling longPolling = new LongPolling();
        longPolling.sendAndReceive();
    }
}
