package com.javabyexamples.spring.core.importconfiguration.selector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CounterImportSelector.class)
public class MainConfiguration {

}
