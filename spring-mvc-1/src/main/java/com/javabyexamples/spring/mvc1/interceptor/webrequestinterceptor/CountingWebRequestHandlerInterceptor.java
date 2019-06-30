package com.javabyexamples.spring.mvc1.interceptor.webrequestinterceptor;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Slf4j
@Component
public class CountingWebRequestHandlerInterceptor implements WebRequestInterceptor {

    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public void preHandle(WebRequest request) throws Exception {
        int requestCount = this.count.incrementAndGet();
        System.out.println("Count: " + requestCount);
        request.setAttribute("count", requestCount, RequestAttributes.SCOPE_REQUEST);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        int requestCount = (int) request.getAttribute("count", RequestAttributes.SCOPE_REQUEST);
        System.out.println("Request has finished:" + requestCount);
    }
}
