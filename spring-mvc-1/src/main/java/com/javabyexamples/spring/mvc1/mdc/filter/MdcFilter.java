package com.javabyexamples.spring.mvc1.mdc.filter;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;

@WebFilter
public class MdcFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
        try {
            MDC.put("CorrelationId", getCorrelationId());
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove("CorrelationId");
        }
    }

    private String getCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
