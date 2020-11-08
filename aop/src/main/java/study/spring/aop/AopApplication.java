package study.spring.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        //SpringApplication.run(AopApplication.class, args);

        ExeTimeCalculator exeTimeCalculator = new ExeTimeCalculator(new ImpeCalculator());
        System.out.println(exeTimeCalculator.factorial(20));

        ExeTimeCalculator exeTimeCalculator2 = new ExeTimeCalculator(new RecCalculator());
        System.out.println(exeTimeCalculator2.factorial(20));
    }
}
