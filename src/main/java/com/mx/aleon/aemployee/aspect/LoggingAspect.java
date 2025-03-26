package com.mx.aleon.aemployee.aspect;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ObjectMapper objectMapper; 

    @Before("execution(* com.mx.aleon.aemployee.controller..*(..))") 
    public void logRequest(JoinPoint joinPoint) {
        try {
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String queryString = request.getQueryString();
            
            log.info("Incoming Request: Method={}, URI={}, QueryString={}", method, uri, queryString);

            if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)) {
                Object[] args = joinPoint.getArgs();
                for (Object arg : args) {
                    if (!(arg instanceof HttpServletRequest)) { // Evitar duplicar la solicitud
                        String jsonPayload = objectMapper.writeValueAsString(arg);
                        log.info("Request Body: {}", jsonPayload);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error logging request: ", e);
        }
    }
}
