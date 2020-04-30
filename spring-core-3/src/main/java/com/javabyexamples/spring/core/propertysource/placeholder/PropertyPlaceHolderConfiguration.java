package com.javabyexamples.spring.core.propertysource.placeholder;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

@Slf4j
@Configuration
public class PropertyPlaceHolderConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        final PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setLocation(new ClassPathResource("propertysource/sample.properties"));
        return placeholderConfigurer;
    }

    @Bean
    public PropertySources propertySources(ConfigurableEnvironment environment) throws IOException {
        final MutablePropertySources propertySources = new MutablePropertySources();
        propertySources.addLast(resourcePropertySource(environment));
        return propertySources;
    }

    @Bean
    public ResourcePropertySource resourcePropertySource(ConfigurableEnvironment environment) throws IOException {
        final ResourcePropertySource resourcePropertySource = new ResourcePropertySource(
          "classpath:propertysource/sample.properties");
        environment.getPropertySources().addLast(resourcePropertySource);
        return resourcePropertySource;
    }
}
