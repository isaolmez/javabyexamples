package com.javabyexamples.spring.mvc1.requestresponsebodyadvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

@ControllerAdvice
public class CustomRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("In supports() method of " + getClass().getSimpleName());
        return methodParameter.getContainingClass() == QuestionController.class && targetType.getTypeName() == Question.class.getTypeName();
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        System.out.println("In beforeBodyRead() method of " + getClass().getSimpleName());
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("In afterBodyRead() method of " + getClass().getSimpleName());
        if (body instanceof Question) {
            Question question = (Question) body;
            question.setDate(new Date());
            return question;
        }

        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                  Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("In handleEmptyBody() method of " + getClass().getSimpleName());
        return body;
    }
}
