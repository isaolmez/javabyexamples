package com.javabyexamples.aws.sqs.jms.content.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SqsConfiguration {

    @Bean
    public AmazonSQS amazonSQS(SqsJmsProperties sqsJmsProperties) {
        final AmazonSQSClientBuilder builder = AmazonSQSClientBuilder.standard();

        if (StringUtils.isNoneBlank(sqsJmsProperties.getSqsEndpoint(),
          sqsJmsProperties.getDefaultRegion())) {
            log.info("Overriding SQS Endpoint to {}", sqsJmsProperties.getSqsEndpoint());
            builder.withEndpointConfiguration(new EndpointConfiguration(
              sqsJmsProperties.getSqsEndpoint(),
              sqsJmsProperties.getDefaultRegion()));
        }

        return builder.build();
    }

    @Bean
    public SQSConnectionFactory sqsConnectionFactory(AmazonSQS amazonSQS) {
        return new SQSConnectionFactory(new ProviderConfiguration(), amazonSQS);
    }

    @Bean
    public SqsJmsProperties sqsJmsProperties() {
        return new SqsJmsProperties();
    }
}
