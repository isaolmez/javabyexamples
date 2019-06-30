package com.javabyexamples.spring.mvc1.mdc.filter;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.slf4j.MDC;

@WebFilter
public class MdcFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
        try {
            MDC.put("CorrelationId", getCorrelationId());
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove("CorrelationId");
        }
    }

    private String getCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
