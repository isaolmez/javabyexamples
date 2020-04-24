package com.javabyexamples.spring.core.usingbean.beannaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingClient {

    @Autowired
    @Qualifier("greetingService")
    private GreetingService service1;

    @Autowired
    @Qualifier("firstGreetingService")
    private GreetingService service2;

    @Autowired
    @Qualifier("theGreetingService")
    private GreetingService service3;

    @Autowired
    @Qualifier("qualifiedGreetingService")
    private GreetingService service4;

    @Autowired
    @Qualifier("serviceWithQualifier")
    private GreetingService service5;
}
