package com.javabyexamples.spring.core.usingbean.beandefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ServiceClient {

    @Autowired
    @Qualifier("serviceOne")
    private ServiceOne dep1;

    @Autowired
    @Qualifier("defaultServiceOne")
    private ServiceOne dep2;

    @Autowired
    @Qualifier("defaultServiceOne")
    private ServiceTwo dep3;

}
