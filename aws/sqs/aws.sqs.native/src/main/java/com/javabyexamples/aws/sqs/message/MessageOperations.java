package com.javabyexamples.aws.sqs.message;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.PurgeQueueRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.javabyexamples.aws.sqs.queue.QueueOperations;
import java.util.List;
import java.util.UUID;

public class MessageOperations {

    private final AmazonSQS amazonSQS = AmazonSQSClientBuilder.defaultClient();

    public List<Message> receiveMessages(String queueUrl) {
        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        return amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
    }

    public List<Message> receiveMessagesWithLimit(String queueUrl) {
        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl)
          .withMaxNumberOfMessages(20)
          .withVisibilityTimeout(60);
        return amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
    }

    public SendMessageResult sendMessage(String queueUrl) {
        final SendMessageRequest sendMessageRequest = new SendMessageRequest(queueUrl, "Another text message!");
        return amazonSQS.sendMessage(sendMessageRequest);
    }

    public SendMessageResult sendMessageWithDelay(String queueUrl) {
        final SendMessageRequest sendMessageRequest = new SendMessageRequest(queueUrl, "Another text message!")
          .withDelaySeconds(10);
        return amazonSQS.sendMessage(sendMessageRequest);
    }

    public void deleteMessage(String queueUrl, String receiptHandle) {
        amazonSQS.deleteMessage(new DeleteMessageRequest(queueUrl, receiptHandle));
    }

    public void purgeQueue(String queueUrl) {
        final PurgeQueueRequest purgeQueueRequest = new PurgeQueueRequest().withQueueUrl(queueUrl);
        amazonSQS.purgeQueue(purgeQueueRequest);
    }

    public void changeMessageVisibility(String queueUrl, String receiptHandle) {
        final ChangeMessageVisibilityRequest changeMessageVisibilityRequest = new ChangeMessageVisibilityRequest()
          .withQueueUrl(queueUrl)
          .withReceiptHandle(receiptHandle)
          .withVisibilityTimeout(900);
        amazonSQS.changeMessageVisibility(changeMessageVisibilityRequest);
    }

    public static void main(String[] args) {
        final MessageOperations messageOperations = new MessageOperations();
        final QueueOperations queueOperations = new QueueOperations();
        final String queueUrl = queueOperations.createQueue(UUID.randomUUID().toString());
        System.out.println("Queue URL: " + queueUrl);
        messageOperations.sendMessage(queueUrl);
        System.out.println("List messages.");
        final List<Message> messages = messageOperations.receiveMessages(queueUrl);
        System.out.println("Change message visibility.");
        messageOperations.changeMessageVisibility(queueUrl, messages.get(0).getReceiptHandle());
//        System.out.println("Delete message.");
//        messageOperations.deleteMessage(queueUrl, messages.get(0).getReceiptHandle());
        System.out.println("Purge queue.");
        messageOperations.purgeQueue(queueUrl);
    }
}
