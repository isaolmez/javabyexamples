package com.javabyexamples.spring.core.conditional.configurationcondition;

import java.util.Map;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MissingServiceCondition implements ConfigurationCondition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        final Map<String, AuditService> auditServiceBeans = beanFactory.getBeansOfType(AuditService.class);
        return auditServiceBeans.isEmpty();
    }

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }
}
