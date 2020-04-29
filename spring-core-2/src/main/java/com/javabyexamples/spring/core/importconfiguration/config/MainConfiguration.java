package com.javabyexamples.spring.core.importconfiguration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CounterConfiguration.class)
public class MainConfiguration {

}
