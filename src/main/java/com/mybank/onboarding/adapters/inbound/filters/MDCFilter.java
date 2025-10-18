package com.mybank.onboarding.adapters.inbound.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class MDCFilter implements Filter {

    private static final String REQUEST_ID = "correlationId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

            String requestId = UUID.randomUUID().toString();
            var correlationId = httpRequest.getHeader("x-correlation-id");

            if(correlationId != null) {
                requestId = UUID.fromString(correlationId).toString();
            }

            MDC.put(REQUEST_ID, requestId);

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            MDC.put(REQUEST_ID, UUID.randomUUID().toString());
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(REQUEST_ID);
        }
    }
}
