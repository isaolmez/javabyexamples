package com.javabyexamples.spring.core.propertysource.useannotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@Configuration
@PropertySource("classpath:propertysource/sample.properties")
public class PropertySourceConfiguration {

}
