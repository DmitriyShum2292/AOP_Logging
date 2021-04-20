package com.example.aopandspel.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectLog {

    @Pointcut("@annotation(Loggable)")
    public void executeLogging(){

    }
    @Before("executeLogging()")
    public void logMethodCall(JoinPoint joinPoint){
        Logger logger = LoggerFactory.getLogger(AspectLog.class);
        StringBuilder message = new StringBuilder("Message: method ");
        message.append(joinPoint.getSignature().getName());
        logger.error(message.toString());
    }
}
