package com.javabyexamples.aws.sqs.jms.visibility.consumer;

import static javax.jms.Session.CLIENT_ACKNOWLEDGE;

import com.javabyexamples.aws.sqs.jms.visibility.config.SqsConfiguration;
import com.javabyexamples.aws.sqs.jms.visibility.producer.ProducerConfiguration;
import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@Import({SqsConfiguration.class, ProducerConfiguration.class})
public class ConsumerConfiguration {

  @Bean
  public MessageConverter jsonMessageConverter() {
    final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    messageConverter.setTypeIdPropertyName("msgPayloadType");
    return messageConverter;
  }

  @Bean
  public MessageListenerA messageListenerA(JmsTemplate jmsTemplate) {
    return new MessageListenerA(jsonMessageConverter(), jmsTemplate);
  }

  @Bean
  public DefaultMessageListenerContainer messageListenerContainerA(
      ConnectionFactory sqsConnectionFactory,
      MessageListener messageListenerA) {
    return getMessageListenerContainer(sqsConnectionFactory,
        messageListenerA,
        "visibility");
  }

  private DefaultMessageListenerContainer getMessageListenerContainer(
      ConnectionFactory sqsConnectionFactory,
      MessageListener messageListener,
      String destinationName) {
    final DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
    listenerContainer.setConnectionFactory(sqsConnectionFactory);
    listenerContainer.setDestinationName(destinationName);
    listenerContainer.setMessageListener(messageListener);
    listenerContainer.setConcurrentConsumers(5);
    listenerContainer.setMaxConcurrentConsumers(10);
    listenerContainer.setAcceptMessagesWhileStopping(true);
    listenerContainer.setSessionTransacted(false);
    listenerContainer.setSessionAcknowledgeMode(CLIENT_ACKNOWLEDGE);
    return listenerContainer;
  }
}
