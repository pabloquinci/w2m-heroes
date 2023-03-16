package com.w2m.logging;

import com.w2m.security.AuthEntryPointJwt;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecTime {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        StringBuilder strLog= new StringBuilder(joinPoint.getSignature().toString());
        strLog.append(" Ejecutado en ");
        strLog.append(executionTime);
        strLog.append( "ms");

        LOGGER.info(strLog.toString());
        return proceed;
    }
}
