package com.javabyexamples.aws.sqs.jms;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MessageConverter;

@Slf4j
public abstract class BaseMessageListener<T> implements MessageListener {

    private final MessageConverter messageConverter;

    public BaseMessageListener(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @Override
    public void onMessage(Message message) {
        try {
            String messageId = message.getJMSMessageID();
            log.debug("Received Message: " + messageId);
            if (!(message instanceof BytesMessage)) {
                throw new IllegalArgumentException("Message not instance of BytesMessage");
            }

            log.info("Is Redelivered?: {}", message.getJMSRedelivered());
            log.info("Delivery count?: {}", message.getIntProperty("JMSXDeliveryCount"));

            log.debug("Unmarshalling Message: " + messageId);
            T jmsEnvelope = (T) messageConverter.fromMessage(message);

            log.debug("Processing Message: " + messageId);
            process(jmsEnvelope);
            log.debug("Processed Message: " + messageId);

        } catch (JMSException e) {
            log.error("Exception Occurred", e);
        }
    }

    protected abstract void process(T jmsEnvelope);
}
