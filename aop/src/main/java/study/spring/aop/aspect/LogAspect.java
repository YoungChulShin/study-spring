package study.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Around("execution(public * study.spring.aop..*(String))")
    public void writeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.printf("===========Method start: %s=============\n", joinPoint.getSignature().getName());
        joinPoint.proceed();
        System.out.printf("===========Method end: %s=============\n", joinPoint.getSignature().getName());
    }
}
