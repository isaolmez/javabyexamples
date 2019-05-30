package com.javabyexamples.spring.core.beannaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService {

    @Autowired
    @Qualifier("simple")
    private AuditService simpleAuditServiceFromQualifier;

    @Autowired
    @Qualifier("theSimpleOne")
    private AuditService simpleAuditServiceFromMethodName;

    @Autowired
    @Qualifier("advanced")
    private AuditService advancedAuditServiceFromComponent;

    @Autowired
    @Qualifier("advanced")
    private AuditService advancedAuditServiceFromQualifier;

    @Autowired
    @Qualifier("legacy")
    private AuditService legacyAuditServiceFromBean;

    @Autowired
    @Qualifier("legacyAuditService")
    private AuditService legacyAuditServiceFromMethodName;

    @Autowired
    @Qualifier("obsolete")
    private AuditService obsoleteAuditServiceFromMethodName;
}
