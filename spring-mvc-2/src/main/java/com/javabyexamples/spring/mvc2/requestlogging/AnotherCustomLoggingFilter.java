package com.javabyexamples.spring.mvc2.requestlogging;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Component
public class AnotherCustomLoggingFilter extends AbstractRequestLoggingFilter {

    @Value("${request.logging.shouldLog}")
    private boolean shouldLog;

    public AnotherCustomLoggingFilter(){
        setIncludeClientInfo(true);
        setIncludeHeaders(true);
        setIncludePayload(true);
        setIncludeQueryString(true);
        setBeforeMessagePrefix("Request started => ");
        setBeforeMessageSuffix("");
        setAfterMessagePrefix("Request ended => ");
        setAfterMessageSuffix("");
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return shouldLog;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }
}
