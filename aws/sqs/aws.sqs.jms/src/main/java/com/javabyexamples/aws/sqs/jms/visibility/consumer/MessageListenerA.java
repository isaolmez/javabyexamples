package com.javabyexamples.aws.sqs.jms.visibility.consumer;

import com.javabyexamples.aws.sqs.jms.BaseMessageListener;
import com.javabyexamples.aws.sqs.jms.visibility.MessageA;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

@Slf4j
public class MessageListenerA extends BaseMessageListener<MessageA> {

    private final JmsTemplate jmsTemplate;

    public MessageListenerA(MessageConverter messageConverter,
      JmsTemplate jmsTemplate) {
        super(messageConverter);
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    protected void process(MessageA jmsEnvelope) {
        final CountDownLatch countDownLatch = new CountDownLatch(120);
        AtomicInteger count = new AtomicInteger();
        final String id = UUID.randomUUID().toString();
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            log.info("Seconds: {}, Id: {}", count.incrementAndGet(), id);
            countDownLatch.countDown();
        }, 0, 1, TimeUnit.SECONDS);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("Error occurred.", e);
        }

        if (1 < 2) {
            throw new RuntimeException("Planned");
        }
    }
}
