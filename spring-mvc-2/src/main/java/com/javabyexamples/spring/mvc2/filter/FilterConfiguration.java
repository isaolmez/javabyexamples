package com.javabyexamples.spring.mvc2.filter;

import java.util.Collections;
import javax.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@ServletComponentScan
public class FilterConfiguration {

    private final DateLoggingFilter dateLoggingFilter;

    @Autowired
    public FilterConfiguration(DateLoggingFilter dateLoggingFilter) {
        this.dateLoggingFilter = dateLoggingFilter;
    }

    @Bean
    public FilterRegistrationBean<DateLoggingFilter> dateLoggingFilterRegistration() {
        FilterRegistrationBean<DateLoggingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(dateLoggingFilter);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
        return filterRegistrationBean;
    }
}
