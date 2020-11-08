package study.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private Calculator cal;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cal.factorial(5);
        cal.factorial(5);
        cal.factorial(7);
        cal.factorial(7);
    }
}
