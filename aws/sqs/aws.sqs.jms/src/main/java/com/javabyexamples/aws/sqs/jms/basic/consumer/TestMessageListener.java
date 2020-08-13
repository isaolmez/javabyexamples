package com.javabyexamples.aws.sqs.jms.basic.consumer;

import com.javabyexamples.aws.sqs.jms.BaseMessageListener;
import com.javabyexamples.aws.sqs.jms.basic.TestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MessageConverter;

@Slf4j
public class TestMessageListener extends BaseMessageListener<TestMessage> {

    public TestMessageListener(MessageConverter messageConverter) {
        super(messageConverter);
    }

    @Override
    protected void process(TestMessage jmsEnvelope) {
        if (1 < 2) {
            throw new RuntimeException("Planned");
        }
    }
}
