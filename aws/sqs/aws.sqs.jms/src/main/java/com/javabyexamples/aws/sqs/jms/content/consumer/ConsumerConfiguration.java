package com.javabyexamples.aws.sqs.jms.content.consumer;

import static javax.jms.Session.CLIENT_ACKNOWLEDGE;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.javabyexamples.aws.sqs.jms.content.config.SqsConfiguration;
import com.javabyexamples.aws.sqs.jms.content.producer.ProducerConfiguration;
import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.connection.JmsTransactionManager;
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
    public JmsTransactionManager jmsTransactionManager(SQSConnectionFactory sqsConnectionFactory) {
        return new JmsTransactionManager(sqsConnectionFactory);
    }

    @Bean
    public DefaultMessageListenerContainer messageListenerContainerA(
      ConnectionFactory sqsConnectionFactory,
      MessageListener messageListenerA) {
        return getMessageListenerContainer(sqsConnectionFactory, messageListenerA, "queue_a");
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
