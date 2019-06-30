package com.javabyexamples.spring.mvc1.deferredinterceptor;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

public class LoggingDeferredResultProcessingInterceptor implements DeferredResultProcessingInterceptor {

    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        System.out.println("beforeConcurrentHandling() in " + getClass().getSimpleName());
    }

    @Override
    public <T> void preProcess(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        System.out.println("preProcess() in " + getClass().getSimpleName());
    }

    @Override
    public <T> void postProcess(NativeWebRequest request, DeferredResult<T> deferredResult, Object concurrentResult) throws Exception {
        System.out.println("postProcess() in " + getClass().getSimpleName());
    }

    @Override
    public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        System.out.println("handleTimeout() in " + getClass().getSimpleName());
        return false;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
        System.out.println("afterCompletion() in " + getClass().getSimpleName());
    }
}