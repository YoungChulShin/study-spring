package study.spring.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy // Aspect 애노테이션이 붙은 빈 객체를 찾아서 빈 객체의 @Pointcut 설정과 @Around 설정을 사용한다
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }
}
