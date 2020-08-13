package com.javabyexamples.aws.sqs.jms.visibility.producer;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.javabyexamples.aws.sqs.jms.visibility.MessageA;
import com.javabyexamples.aws.sqs.jms.visibility.config.SqsConfiguration;
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
        return () -> jmsTemplate.convertAndSend("visibility",
          new MessageA("Message A"));
    }

    private MessageConverter jsonMessageConverter() {
        final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("msgPayloadType");
        return messageConverter;
    }
}
