package study.spring.multithread.cron;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MultiSampleScheduler {

  /*
  8초가 걸리는 작업을 2개 실행한다.
  multi thread이기 때문에 각각의 작업이 별도의 스레드로 실행되고 간섭하지 않는다.

  [pool-2-thread-1](2023-10-14T22:07:50.005826) [cron] 5초마다 실행 - 시작
  [pool-2-thread-2](2023-10-14T22:07:51.306855) [fixedRate] 5초 이후에 실행 - 시작
  [pool-2-thread-1](2023-10-14T22:07:58.011753) [cron] 5초마다 실행 - 종료
  [pool-2-thread-2](2023-10-14T22:07:59.312425) [fixedRate] 5초 이후에 실행 - 종료
  [pool-2-thread-1](2023-10-14T22:07:59.313240) [fixedRate] 5초 이후에 실행 - 시작
  [pool-2-thread-3](2023-10-14T22:08:00.010294) [cron] 5초마다 실행 - 시작
  [pool-2-thread-1](2023-10-14T22:08:07.318417) [fixedRate] 5초 이후에 실행 - 종료
  [pool-2-thread-2](2023-10-14T22:08:07.319277) [fixedRate] 5초 이후에 실행 - 시작
  [pool-2-thread-3](2023-10-14T22:08:08.012269) [cron] 5초마다 실행 - 종료
  [pool-2-thread-4](2023-10-14T22:08:10.005115) [cron] 5초마다 실행 - 시작
  [pool-2-thread-2](2023-10-14T22:08:15.323917) [fixedRate] 5초 이후에 실행 - 종료
  [pool-2-thread-1](2023-10-14T22:08:15.324272) [fixedRate] 5초 이후에 실행 - 시작
  [pool-2-thread-4](2023-10-14T22:08:18.010474) [cron] 5초마다 실행 - 종료
  [pool-2-thread-5](2023-10-14T22:08:20.009070) [cron] 5초마다 실행 - 시작
  [pool-2-thread-1](2023-10-14T22:08:23.329411) [fixedRate] 5초 이후에 실행 - 종료
  [pool-2-thread-3](2023-10-14T22:08:23.330307) [fixedRate] 5초 이후에 실행 - 시작
   */
  @Scheduled(cron = "*/5 * * * * *")
  public void longCronTask() throws InterruptedException {
    printLog("[cron] 5초마다 실행 - 시작");
    Thread.sleep(8000);
    printLog("[cron] 5초마다 실행 - 종료");
  }

  @Scheduled(fixedRate = 5000, initialDelay = 3000)
  public void longFixedRateTask() throws InterruptedException {
    printLog("[fixedRate] 5초 이후에 실행 - 시작");
    Thread.sleep(8000);
    printLog("[fixedRate] 5초 이후에 실행 - 종료");
  }

  public void printLog(String message) {
    System.out.println("[" + Thread.currentThread().getName() + "](" + LocalDateTime.now() + ") " + message);
  }
}
