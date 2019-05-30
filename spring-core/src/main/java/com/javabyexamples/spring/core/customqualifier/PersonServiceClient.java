package com.javabyexamples.spring.core.customqualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceClient {

    @Autowired
    @Version1
    private PersonService personServiceV1;

    @Autowired
    @Version2
    private PersonService personServiceV2;

    public void start() {
        personServiceV1.hello();
        personServiceV2.hello();
    }
}
