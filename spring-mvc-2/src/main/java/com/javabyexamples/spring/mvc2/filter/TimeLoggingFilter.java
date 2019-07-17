package com.javabyexamples.spring.mvc2.filter;

import java.io.IOException;
import java.sql.Time;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TimeLoggingFilter extends HttpFilter implements Ordered {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
        log.info("New request at time: {}", new Time(System.currentTimeMillis()));

        chain.doFilter(request, response);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 99;
    }
}
