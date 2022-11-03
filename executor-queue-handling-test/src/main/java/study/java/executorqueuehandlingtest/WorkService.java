package study.java.executorqueuehandlingtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WorkService {

    private final Logger logger = LoggerFactory.getLogger(WorkService.class);

    @Async("defaultExecutor")
    public void work(int number) {
        doWork(number);
    }

    @Async("discardExecutor")
    public void workDiscard(int number) {
        doWork(number);
    }

    @Async("loggingExecutor")
    public void workCustom(int number) {
        doWork(number);
    }

    private void doWork(int number) {
        try {
            logger.info("작업 시작: {}", number);
            Thread.sleep(500);
            logger.info("작업 완료: {}", number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
