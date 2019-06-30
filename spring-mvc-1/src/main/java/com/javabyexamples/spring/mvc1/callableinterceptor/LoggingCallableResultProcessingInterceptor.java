package com.javabyexamples.spring.mvc1.callableinterceptor;

import java.util.concurrent.Callable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

public class LoggingCallableResultProcessingInterceptor implements CallableProcessingInterceptor {

    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest request, Callable<T> task) throws Exception {
        System.out.println("beforeConcurrentHandling() in " + getClass().getSimpleName());
    }

    @Override
    public <T> void preProcess(NativeWebRequest request, Callable<T> task) throws Exception {
        System.out.println("preProcess() in " + getClass().getSimpleName());
    }

    @Override
    public <T> void postProcess(NativeWebRequest request, Callable<T> task,
            Object concurrentResult) throws Exception {
        System.out.println("postProcess() in " + getClass().getSimpleName());
    }

    @Override
    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        System.out.println("handleTimeout() in " + getClass().getSimpleName());
        return RESULT_NONE;
    }

    @Override
    public <T> Object handleError(NativeWebRequest request, Callable<T> task, Throwable t) throws Exception {
        System.out.println("handleError() in " + getClass().getSimpleName());
        return RESULT_NONE;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest request, Callable<T> task) throws Exception {
        System.out.println("afterCompletion() in " + getClass().getSimpleName());
    }
}