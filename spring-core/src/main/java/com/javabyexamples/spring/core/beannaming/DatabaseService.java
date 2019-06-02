package com.javabyexamples.spring.core.beannaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService {

    @Autowired
    @Qualifier("simple")
    private AuditService simpleAuditServiceFromBean;

    @Autowired
    @Qualifier("qualifiedSimple")
    private AuditService simpleAuditServiceFromQualifier;

    @Autowired
    @Qualifier("advanced")
    private AuditService advancedAuditServiceFromComponent;

    @Autowired
    @Qualifier("qualifiedAdvanced")
    private AuditService advancedAuditServiceFromQualifier;

    @Autowired
    @Qualifier("legacyAuditService")
    private AuditService legacyAuditServiceFromDefault;

    @Autowired
    @Qualifier("obsolete")
    private AuditService obsoleteAuditServiceFromMethodName;
}
