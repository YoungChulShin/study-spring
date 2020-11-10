package study.spring.async_transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsyncTransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncTransactionalApplication.class, args);
    }

}
