package com.javabyexamples.spring.mvc1.currentrequest.requestscope;

import com.javabyexamples.spring.mvc1.currentrequest.Person;
import com.javabyexamples.spring.mvc1.currentrequest.PersonController;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

@ControllerAdvice
public class ForRequestScopeRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Autowired
    private RequestScopedContext requestScopedContext;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return targetType.getTypeName() == Person.class.getTypeName() && methodParameter.getContainingClass() == PersonController.class;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        if (body instanceof Person) {
            requestScopedContext.set((Person) body);
        }

        return body;
    }
}
