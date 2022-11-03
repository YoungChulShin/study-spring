package study.java.executorqueuehandlingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ExecutorQueueHandlingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecutorQueueHandlingTestApplication.class, args);
    }

}
