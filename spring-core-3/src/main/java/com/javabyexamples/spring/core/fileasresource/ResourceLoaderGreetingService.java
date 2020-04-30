package com.javabyexamples.spring.core.fileasresource;

import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class ResourceLoaderGreetingService {

    private final ResourceLoader resourceLoader;

    @Autowired
    public ResourceLoaderGreetingService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void greet() throws IOException {
        final Resource resource = resourceLoader.getResource("classpath:fileasresource/hello.txt");
        final InputStreamReader reader = new InputStreamReader(resource.getInputStream());
        System.out.println(FileCopyUtils.copyToString(reader));
    }
}
