package study.java.executorqueuehandlingtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class TaskExecutorConfig {

    /**
     * 기본 ThreadPoolTaskExecutor
     * pool size: 1
     * queue capacity: 1
     */
    @Bean
    public ThreadPoolTaskExecutor defaultExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(1);
        taskExecutor.setQueueCapacity(1);

        return taskExecutor;
    }

    /**
     * DiscardPolicy ThreadPoolTaskExecutor
     * pool size: 1
     * queue capacity: 1
     * queue가 다 차면 이후 요청 무시
     */
    @Bean
    public ThreadPoolTaskExecutor discardExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(1);
        taskExecutor.setQueueCapacity(1);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        return taskExecutor;
    }

    /**
     * Custom ThreadPoolTaskExecutor - Logging Executor
     * pool size: 1
     * queue capacity: 1
     * queue가 다 차면 로그로 정보를 기록
     */
    @Bean
    public ThreadPoolTaskExecutor loggingExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(1);
        taskExecutor.setQueueCapacity(1);
        taskExecutor.setRejectedExecutionHandler(new LoggingPolicy());

        return taskExecutor;
    }
}
