package com.javabyexamples.spring.core.beannaming;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfiguration {

    @Bean("simple")
    @Qualifier("qualifiedSimple")
    public AuditService theSimpleOne() {
        return new SimpleAuditService();
    }

    @Bean
    public AuditService obsolete() {
        return new LegacyAuditService();
    }
}
