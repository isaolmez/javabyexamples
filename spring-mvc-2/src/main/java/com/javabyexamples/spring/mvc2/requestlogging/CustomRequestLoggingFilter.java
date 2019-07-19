package com.javabyexamples.spring.mvc2.requestlogging;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Slf4j
@Component
public class CustomRequestLoggingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
      throws IOException, ServletException {
        final HttpServletRequest currentRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse currentResponse = (HttpServletResponse) servletResponse;

        StringBuffer requestURL = currentRequest.getRequestURL();
        log.info("Request URL: {}", requestURL);
        try {
            chain.doFilter(currentRequest, servletResponse);
        } finally {
            int status = currentResponse.getStatus();
            log.info("Response status: {}", status);
        }
    }
}