package com.javabyexamples.spring.core.fileasresource;

import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class AsDependencyGreetingService {

    @Value("classpath:fileasresource/hello.txt")
    private Resource greeting;

    public void greet() throws IOException {
        final InputStreamReader reader = new InputStreamReader(greeting.getInputStream());
        System.out.println(FileCopyUtils.copyToString(reader));
    }
}
