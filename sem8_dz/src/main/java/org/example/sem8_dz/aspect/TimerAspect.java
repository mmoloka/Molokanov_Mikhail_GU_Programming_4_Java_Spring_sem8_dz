package org.example.sem8_dz.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("within(@org.example.sem8_dz.aspect.Timer *)")
    public void beansAnnotatedWith(){}

    @Pointcut("@annotation(org.example.sem8_dz.aspect.Timer)")
    public void methodsAnnotatedWith(){}

    @Around("beansAnnotatedWith() || methodsAnnotatedWith()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info("{} - {} #{}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), elapsedTime);
        return result;
    }
}
