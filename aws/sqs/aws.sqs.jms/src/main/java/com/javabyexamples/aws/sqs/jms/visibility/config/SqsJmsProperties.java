package com.javabyexamples.aws.sqs.jms.visibility.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class SqsJmsProperties {

    @Value("${jms.provider.sqs.endpoint}")
    private String sqsEndpoint;

    @Value("${jms.provider.sqs.defaultRegion}")
    private String defaultRegion;
}
