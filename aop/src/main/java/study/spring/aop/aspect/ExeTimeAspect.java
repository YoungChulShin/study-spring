package study.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExeTimeAspect {

    @Pointcut("execution(public * study..*(..))")
    private void publicTarget() {

    }

    @Around("publicTarget") // publicTarget() 메서드의 정의한 Pointcut에 공통 기능을 적용한다
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long
        }
    }

}
