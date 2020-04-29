package com.javabyexamples.spring.core.componentscan.custom;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
  basePackages = "com.javabyexamples.spring.core.componentscan.level1",
  includeFilters = {@Filter(type = FilterType.CUSTOM, classes = PackageAwareTypeFilter.class)})
public class CustomFilterConfiguration {

}
