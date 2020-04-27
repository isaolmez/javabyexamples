package com.javabyexamples.spring.core.stereotypes;

import com.javabyexamples.spring.core.stereotypes.metaannotation.Generator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

@Configuration
@ComponentScan
public class Application {

    @Bean
    public PersistenceExceptionTranslationPostProcessor translationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
          Application.class);

        Generator generator = applicationContext.getBean(Generator.class);
        generator.generate();

        DepartmentRepository departmentRepository = applicationContext.getBean(DepartmentRepository.class);
        departmentRepository.save();

        applicationContext.close();
    }
}
