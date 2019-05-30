package com.javabyexamples.spring.core.componentscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.javabyexamples.spring.core.componentscan.level1")
public class ExplicitPackageConfiguration {

}
