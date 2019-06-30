package com.javabyexamples.spring.mvc1.interceptor;

import com.javabyexamples.spring.mvc1.interceptor.webrequestinterceptor.CountingWebRequestHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final LoggingHandlerInterceptor loggingHandlerInterceptor;
    private final WatchHandlerInterceptor watchHandlerInterceptor;
    private final CountingWebRequestHandlerInterceptor countingWebRequestHandlerInterceptor;

    @Autowired
    public InterceptorConfiguration(LoggingHandlerInterceptor loggingHandlerInterceptor,
            WatchHandlerInterceptor watchHandlerInterceptor,
            CountingWebRequestHandlerInterceptor countingWebRequestHandlerInterceptor) {
        this.loggingHandlerInterceptor = loggingHandlerInterceptor;
        this.watchHandlerInterceptor = watchHandlerInterceptor;
        this.countingWebRequestHandlerInterceptor = countingWebRequestHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingHandlerInterceptor).excludePathPatterns("/api/**");
        registry.addInterceptor(watchHandlerInterceptor).addPathPatterns("/api/**");
        registry.addWebRequestInterceptor(countingWebRequestHandlerInterceptor);
    }
}
