package com.javabyexamples.spring.core.componentscan.assignable;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
  basePackages = "com.javabyexamples.spring.core.componentscan.level1",
  includeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Object.class)})
public class AssignableFilterConfiguration {

}
