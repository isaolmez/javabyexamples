package com.javabyexamples.spring.core.usingbean.beannaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ServiceClient {

    @Autowired
    @Qualifier("serviceOne")
    private ServiceOne dep1;

    @Autowired
    @Qualifier("firstService")
    private ServiceOne dep2;

    @Autowired
    @Qualifier("onlyService")
    private ServiceOne dep3;

    @Autowired
    @Qualifier("qualifiedService")
    private ServiceOne dep4;

    @Autowired
    @Qualifier("serviceOneWithQualifier")
    private ServiceOne dep5;
}
