package com.example.aopandspel.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    @AfterReturning(pointcut = "executeLogging()",returning = "returnValue")
    public void logMethodCallWithReturnValue(JoinPoint joinPoint,Object returnValue){
        Logger logger = LoggerFactory.getLogger(AspectLog.class);
        StringBuilder message = new StringBuilder("Another Message: method ");
        message.append(joinPoint.getSignature().getName());
        logger.info(message.toString());
    }
    @Around("executeLogging()")
    public Object aroundLogMethodCall(ProceedingJoinPoint joinPoint) throws Throwable{

        Logger logger = LoggerFactory.getLogger(AspectLog.class);

        long startTime = System.currentTimeMillis();

        Object returnValue = joinPoint.proceed();

        StringBuilder message = new StringBuilder("With Time: method ");
        message.append(joinPoint.getSignature().getName());

        long spendTime = System.currentTimeMillis()-startTime;
        message.append(" time: "+spendTime+" ms");
        logger.info(message.toString());
        return returnValue;
    }
}
