package study.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Order(2)
@Component
public class ExeTimeAspect {

    // 공통으로 실행할 대상 (=Advice를 적용할 대상)
//    @Pointcut("execution(public * study.spring.aop..*(..))")
//    private void publicTarget() {
//    }

    // publicTarget() 메서드의 정의한 Pointcut에 공통 기능을 적용한다
    // joinPoint는 프록시 대상 객체의 메서드를 호출할 때 사용
    @Around("CommonPointcut.commonTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.nanoTime();
            Signature signature = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    signature.getName(),    // 호출되는 메서드 이름
                    Arrays.toString(joinPoint.getArgs()),   // 호출되는 파라미터 이름
                    (finish - start));
        }
    }
}
