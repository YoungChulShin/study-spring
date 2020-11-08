package study.spring.aop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {

    @Pointcut("execution(public * study.spring.aop..*(long))")
    public void commonTarget() {
    }
}
