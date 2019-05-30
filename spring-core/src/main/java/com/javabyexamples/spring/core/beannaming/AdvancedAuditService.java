package com.javabyexamples.spring.core.beannaming;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("advanced")
@Qualifier("qualifiedAdvanced")
public class AdvancedAuditService implements AuditService {

}
