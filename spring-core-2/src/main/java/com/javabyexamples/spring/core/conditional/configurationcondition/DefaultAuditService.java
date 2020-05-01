package com.javabyexamples.spring.core.conditional.configurationcondition;

import org.springframework.stereotype.Component;

@Component
public class DefaultAuditService implements AuditService {

    @Override
    public void audit() {
        // Do audit.
    }
}
