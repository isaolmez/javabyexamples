package com.javabyexamples.spring.core.fileasresource;

import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class ResourceGreetingService {

    public void greet() throws IOException {
        final Resource resource = new ClassPathResource("fileasresource/hello.txt");
        final InputStreamReader reader = new InputStreamReader(resource.getInputStream());
        System.out.println(FileCopyUtils.copyToString(reader));
    }
}
