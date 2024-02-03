package org.example.sem8_dz.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RecoverExceptionAspect {

    @Pointcut("@annotation(org.example.sem8_dz.aspect.RecoverException)")
    public void methodsAnnotatedWith() {
    }

    @Around("methodsAnnotatedWith()")
    public Object recoverException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();

        } catch (Throwable exception) {
            if (isExceptionRecover(exception, joinPoint)) throw exception;
        }
        return null;
    }

    private boolean isExceptionRecover(Throwable exception, ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RecoverException annotation = signature.getMethod().getAnnotation(RecoverException.class);
        for (Class<? extends RuntimeException> ex : annotation.noRecoverFor()) {
            if (exception.getClass().isAssignableFrom(ex)) return true;
        }
        return false;
    }
}
