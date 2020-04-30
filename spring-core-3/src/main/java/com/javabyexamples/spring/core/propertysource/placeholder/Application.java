package com.javabyexamples.spring.core.propertysource.placeholder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
@ComponentScan
public class Application {

    @Bean
    public InitializingBean accessFromEnvironment(Environment environment) {
        return () -> log.info("Access from Environment: hello={}", environment.getProperty("hello"));
    }

    @Bean
    public InitializingBean resolvePlaceHolder(@Value("${hello}") String hello) {
        return () -> log.info("Resolve the placeholder: hello={}", hello);
    }

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(
          Application.class);

        applicationContext.close();
    }
}
