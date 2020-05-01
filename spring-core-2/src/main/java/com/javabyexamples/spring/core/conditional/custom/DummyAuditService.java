package com.javabyexamples.spring.core.conditional.custom;

import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingService(AuditService.class)
public class DummyAuditService implements AuditService {

    @Override
    public void audit() {
        // Do nothing.
    }
}
