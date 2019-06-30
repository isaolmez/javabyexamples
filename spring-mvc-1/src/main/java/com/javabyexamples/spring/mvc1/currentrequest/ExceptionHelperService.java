package com.javabyexamples.spring.mvc1.currentrequest;

import com.javabyexamples.spring.mvc1.currentrequest.requestscope.RequestScopedContext;
import com.javabyexamples.spring.mvc1.currentrequest.threadlocal.ThreadLocalRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class ExceptionHelperService {

    @Autowired
    private RequestScopedContext requestScopedContext;

    public Person getAndProcessFromThreadLocal() {
        return ThreadLocalRequestContext.get();
    }

    public Person getAndProcessPersonFromRequestScope() {
        return requestScopedContext.get();
    }

    public Person getAndProcessPersonFromRequestContextHolder() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        return (Person) requestAttributes.getAttribute("person", RequestAttributes.SCOPE_REQUEST);
    }
}
