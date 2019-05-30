package com.javabyexamples.spring.core.annotationconfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.javabyexamples.spring.core.annotationconfig")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);

        ChatService chatService = applicationContext.getBean(ChatService.class);
        chatService.chat();

        applicationContext.close();
    }
}
