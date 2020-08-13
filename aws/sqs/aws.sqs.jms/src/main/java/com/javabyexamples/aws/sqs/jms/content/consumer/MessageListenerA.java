package com.javabyexamples.aws.sqs.jms.content.consumer;

import com.javabyexamples.aws.sqs.jms.BaseMessageListener;
import com.javabyexamples.aws.sqs.jms.content.MessageA;
import com.javabyexamples.aws.sqs.jms.content.MessageB;
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
    jmsTemplate.convertAndSend("queue_b", new MessageB("Message b"));

    if (1 < 2) {
      throw new RuntimeException("Planned");
    }
  }
}
