package com.javabyexamples.spring.core.beanlookup;

import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SampleCommand implements Command {

    private final String id =
      UUID.randomUUID().toString();

    @Override
    public String execute() {
        return "Id: " + id;
    }
}
