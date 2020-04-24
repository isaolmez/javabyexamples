package com.javabyexamples.spring.core.beanlookup;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public abstract class CommandManager {

    public String process() {
        System.out.println("Processing...");
        Command command = createCommand();
        return command.execute();
    }

    public String processWithQualifier() {
        System.out.println("Processing with qualifier...");
        Command command = createCommandWithQualifier();
        return command.execute();
    }

    @Lookup
    protected abstract Command createCommand();

    @Lookup(value = "sampleCommand")
    protected abstract Command createCommandWithQualifier();
}
