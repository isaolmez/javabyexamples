package com.javabyexamples.spring.mvc1.suffixmatch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class PathMatcherConfiguration implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
                .setUseSuffixPatternMatch(false)
                .setUseTrailingSlashMatch(false)
                .setUseRegisteredSuffixPatternMatch(false)
                .setPathMatcher(antPathMatcher())
                .setUrlPathHelper(urlPathHelper())
                .addPathPrefix("api", HandlerTypePredicate.forBasePackage("com.javabyexamples.spring.mvc1.suffixmatch.api"));
    }

    @Bean
    public UrlPathHelper urlPathHelper() {
        return new UrlPathHelper();
    }

    @Bean
    public PathMatcher antPathMatcher() {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        antPathMatcher.setCaseSensitive(false);
        return antPathMatcher;
    }
}
