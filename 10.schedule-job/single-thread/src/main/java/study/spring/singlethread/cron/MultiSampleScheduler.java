package study.spring.singlethread.cron;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MultiSampleScheduler {

  /*
  8초가 걸리는 작업을 2개 실행한다.
  single thread이기 때문에 각각의 작업이 서로에게 영향을 받는다. 즉, cron이 정상적으로 동작하지 않는다.

  [scheduling-1](2023-10-14T22:09:13.009641) [cron] 5초마다 실행 - 종료
  [scheduling-1](2023-10-14T22:09:13.011113) [fixedRate] 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T22:09:21.013432) [fixedRate] 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T22:09:21.013734) [fixedRate] 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T22:09:29.018910) [fixedRate] 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T22:09:29.019709) [cron] 5초마다 실행 - 시작
  [scheduling-1](2023-10-14T22:09:37.024920) [cron] 5초마다 실행 - 종료
  [scheduling-1](2023-10-14T22:09:37.026185) [fixedRate] 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T22:09:45.029828) [fixedRate] 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T22:09:45.030489) [fixedRate] 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T22:09:53.035696) [fixedRate] 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T22:09:53.036404) [fixedRate] 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T22:10:01.038634) [fixedRate] 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T22:10:01.039349) [fixedRate] 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T22:10:09.044533) [fixedRate] 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T22:10:09.045231) [fixedRate] 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T22:10:17.046959) [fixedRate] 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T22:10:17.047702) [cron] 5초마다 실행 - 시작
  [scheduling-1](2023-10-14T22:10:25.049881) [cron] 5초마다 실행 - 종료
  [scheduling-1](2023-10-14T22:10:25.051360) [fixedRate] 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T22:10:33.053260) [fixedRate] 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T22:10:33.054026) [fixedRate] 5초 이후에 실행 - 시작
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
