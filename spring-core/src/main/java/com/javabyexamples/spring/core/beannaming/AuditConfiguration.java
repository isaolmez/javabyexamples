package com.javabyexamples.spring.core.beannaming;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfiguration {

    @Bean
    @Qualifier("simple")
    public AuditService theSimpleOne() {
        return new SimpleAuditService();
    }

    @Bean("legacy")
    public AuditService legacyAuditService() {
        return new LegacyAuditService();
    }

    @Bean
    public AuditService obsolete(){
       return new LegacyAuditService();
    }
}
