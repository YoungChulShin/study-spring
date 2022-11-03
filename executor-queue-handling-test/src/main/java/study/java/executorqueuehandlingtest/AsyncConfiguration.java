package study.java.executorqueuehandlingtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class AsyncConfiguration {

    /**
     * Default Policy is Abort policy
     * Throws 'RejectedExecutionException'
     */
    @Bean
    public ThreadPoolExecutor defaultExecutor() {
        return new ThreadPoolExecutor(
                4,
                4,
                0,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<>()
        );
    }

    /**
     * Discard last task
     */
    @Bean
    public ThreadPoolExecutor discardExecutor() {
        return new ThreadPoolExecutor(
                4,
                4,
                0,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }

    /**
     * Custom policy which implements RejectedExecutionHandler
     * In this case, log error data
     */
    @Bean
    public ThreadPoolExecutor loggingExecutor() {
        return new ThreadPoolExecutor(
                4,
                4,
                0,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                new LoggingPolicy()
        );
    }
}
