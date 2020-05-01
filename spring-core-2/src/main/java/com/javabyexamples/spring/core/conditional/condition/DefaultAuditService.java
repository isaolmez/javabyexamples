package com.javabyexamples.spring.core.conditional.condition;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(AuditEnabledCondition.class)
public class DefaultAuditService implements AuditService {

    @Override
    public void audit() {
        // Do audit...
    }
}
