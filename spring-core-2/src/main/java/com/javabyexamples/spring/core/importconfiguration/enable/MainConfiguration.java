package com.javabyexamples.spring.core.importconfiguration.enable;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CounterConfiguration.class)
public class MainConfiguration {

}
