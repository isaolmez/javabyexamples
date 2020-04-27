package com.javabyexamples.spring.core.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolConfiguration {

    @Bean
    public SchoolService schoolService() {
        return new SchoolService(classService());
    }

    @Bean
    public ClassService classService() {
        return new ClassService();
    }

    @Bean
    public TeacherService teacherService() {
        return new TeacherService();
    }

    @Bean
    public PrincipalService principalService() {
        return new PrincipalService();
    }
}
