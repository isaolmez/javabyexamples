package com.javabyexamples.spring.core.conditional.configurationcondition;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(MissingServiceCondition.class)
public class DummyAuditService implements AuditService {

    @Override
    public void audit() {
        // Do nothing.
    }
}
