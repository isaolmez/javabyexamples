package com.javabyexamples.spring.core.conditional.custom;

import java.util.Map;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MissingServiceCondition implements ConfigurationCondition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final Map<String, Object> annotationAttributes = metadata
          .getAnnotationAttributes(ConditionalOnMissingService.class.getName());
        final Class<?> beanClass = (Class<?>) annotationAttributes.get("value");
        final Map<String, ?> auditServiceBeans = context.getBeanFactory().getBeansOfType(beanClass);
        return auditServiceBeans.isEmpty();
    }

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }
}
