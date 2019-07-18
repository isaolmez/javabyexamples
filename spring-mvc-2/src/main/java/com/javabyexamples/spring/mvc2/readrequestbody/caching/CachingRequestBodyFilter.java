package com.javabyexamples.spring.mvc2.readrequestbody.caching;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Component
public class CachingRequestBodyFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
      throws IOException, ServletException {
        HttpServletRequest currentRequest = (HttpServletRequest) servletRequest;
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(currentRequest);

        chain.doFilter(wrappedRequest, servletResponse);
    }
}