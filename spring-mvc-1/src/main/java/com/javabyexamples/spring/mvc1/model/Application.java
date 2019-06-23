package com.javabyexamples.spring.mvc1.model;

import com.javabyexamples.spring.mvc1.common.TemplateResolverProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        return TemplateResolverProvider.chapterTemplateResolver("templates/model/");
    }

}
