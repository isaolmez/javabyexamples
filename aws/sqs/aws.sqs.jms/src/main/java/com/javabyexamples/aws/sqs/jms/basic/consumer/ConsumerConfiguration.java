package com.javabyexamples.aws.sqs.jms.basic.consumer;

import static javax.jms.Session.CLIENT_ACKNOWLEDGE;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.javabyexamples.aws.sqs.jms.basic.config.SqsConfiguration;
import javax.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@Import(SqsConfiguration.class)
public class ConsumerConfiguration {

    @Bean
    public MessageConverter jsonMessageConverter() {
        final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("msgPayloadType");
        return messageConverter;
    }

    @Bean
    public TestMessageListener testMessageListener() {
        return new TestMessageListener(jsonMessageConverter());
    }

    @Bean
    public JmsTransactionManager jmsTransactionManager(SQSConnectionFactory sqsConnectionFactory) {
        return new JmsTransactionManager(sqsConnectionFactory);
    }

    @Bean
    public DefaultMessageListenerContainer messageListenerContainer(
      ConnectionFactory sqsConnectionFactory) {
        final DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
        listenerContainer.setConnectionFactory(sqsConnectionFactory);
        listenerContainer.setDestinationName("test_queue_1");
        listenerContainer.setMessageListener(testMessageListener());
        listenerContainer.setConcurrentConsumers(5);
        listenerContainer.setMaxConcurrentConsumers(10);
        listenerContainer.setAcceptMessagesWhileStopping(true);
        listenerContainer.setSessionTransacted(false);
        listenerContainer.setSessionAcknowledgeMode(CLIENT_ACKNOWLEDGE);
        return listenerContainer;
    }
}
