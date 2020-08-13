package com.javabyexamples.aws.sqs.jms.basic.producer;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.javabyexamples.aws.sqs.jms.basic.TestMessage;
import com.javabyexamples.aws.sqs.jms.basic.config.SqsConfiguration;
import java.util.UUID;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@Import(SqsConfiguration.class)
public class ProducerConfiguration {

  @Bean
  public JmsTemplate jmsTemplate(SQSConnectionFactory sqsConnectionFactory) {
    final JmsTemplate jmsTemplate = new JmsTemplate(sqsConnectionFactory);
    jmsTemplate.setMessageConverter(jsonMessageConverter());
    return jmsTemplate;
  }

  @Bean
  public InitializingBean initializingBean(JmsTemplate jmsTemplate) {
    return new InitializingBean() {
      @Override
      public void afterPropertiesSet() throws Exception {
        jmsTemplate.convertAndSend("test_queue_1",
            new TestMessage("First message", UUID.randomUUID().toString()));
      }
    };
  }

  private MessageConverter jsonMessageConverter() {
    final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    messageConverter.setTypeIdPropertyName("msgPayloadType");
    return messageConverter;
  }
}
