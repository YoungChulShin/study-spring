package study.spring.multithread.cron;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleScheduler {

  /*
  single thread와 동작은 같은데 thread pool을 이용하기 때문에 실행될 때마다 스레드가 다르다.

  [pool-2-thread-1](2023-10-14T21:52:30.006073) 5초마다 실행
  [pool-2-thread-1](2023-10-14T21:52:35.005293) 5초마다 실행
  [pool-2-thread-2](2023-10-14T21:52:40.004994) 5초마다 실행
  [pool-2-thread-1](2023-10-14T21:52:45.001352) 5초마다 실행
  [pool-2-thread-3](2023-10-14T21:52:50.005458) 5초마다 실행
  [pool-2-thread-2](2023-10-14T21:52:55.001327) 5초마다 실행
   */
//  @Scheduled(cron = "*/5 * * * * *")
//  public void cronTask() {
//    printLog("5초마다 실행");
//  }

  /*
  single thread와 동작이 동일하다. 실행되는 thread는 바뀌는데, 중복 실행되지는 않는다.

  [pool-2-thread-1](2023-10-14T21:54:15.005348) 5초마다 실행 - 시작
  [pool-2-thread-1](2023-10-14T21:54:23.010975) 5초마다 실행 - 종료
  [pool-2-thread-1](2023-10-14T21:54:25.005291) 5초마다 실행 - 시작
  [pool-2-thread-1](2023-10-14T21:54:33.010494) 5초마다 실행 - 종료
  [pool-2-thread-2](2023-10-14T21:54:35.005421) 5초마다 실행 - 시작
  [pool-2-thread-2](2023-10-14T21:54:43.008246) 5초마다 실행 - 종료
  [pool-2-thread-1](2023-10-14T21:54:45.004616) 5초마다 실행 - 시작
  [pool-2-thread-1](2023-10-14T21:54:53.006157) 5초마다 실행 - 종료
   */
//  @Scheduled(cron = "*/5 * * * * *")
//  public void longCronTask() throws InterruptedException {
//    printLog("5초마다 실행 - 시작");
//    Thread.sleep(8000);
//    printLog("5초마다 실행 - 종료");
//  }

  /*
  single thread와 동작이 동일하다. thread는 실행될 때마다 변경된다.

  [pool-2-thread-1](2023-10-14T21:56:17.489353) 5초 이후에 실행
  [pool-2-thread-1](2023-10-14T21:56:22.497799) 5초 이후에 실행
  [pool-2-thread-2](2023-10-14T21:56:27.498925) 5초 이후에 실행
  [pool-2-thread-1](2023-10-14T21:56:32.501401) 5초 이후에 실행
  [pool-2-thread-3](2023-10-14T21:56:37.503498) 5초 이후에 실행
   */
//  @Scheduled(fixedDelay = 5000, initialDelay = 3000)
//  public void cronTask() {
//    printLog("5초 이후에 실행");
//  }

  /*
  single thread와 동작이 동일하다. thread는 실행될 때마다 변경된다.

  [pool-2-thread-1](2023-10-14T21:59:33.200429) 5초 이후에 실행 - 시작
  [pool-2-thread-1](2023-10-14T21:59:41.205745) 5초 이후에 실행 - 종료
  [pool-2-thread-3](2023-10-14T21:59:46.211279) 5초 이후에 실행 - 시작
  [pool-2-thread-3](2023-10-14T21:59:54.216699) 5초 이후에 실행 - 종료
  [pool-2-thread-2](2023-10-14T21:59:59.217962) 5초 이후에 실행 - 시작
  [pool-2-thread-2](2023-10-14T22:00:07.223100) 5초 이후에 실행 - 종료
   */
//  @Scheduled(fixedDelay = 5000, initialDelay = 3000)
//  public void longCronTask() throws InterruptedException {
//    printLog("5초 이후에 실행 - 시작");
//    Thread.sleep(8000);
//    printLog("5초 이후에 실행 - 종료");
//  }

  /*
  single thread와 동작이 동일하다. thread는 실행될 때마다 변경된다.

  [pool-2-thread-1](2023-10-14T22:01:27.208383) 5초마다 실행
  [pool-2-thread-3](2023-10-14T22:01:32.209569) 5초마다 실행
  [pool-2-thread-2](2023-10-14T22:01:37.209513) 5초마다 실행
  [pool-2-thread-4](2023-10-14T22:01:42.206237) 5초마다 실행
  [pool-2-thread-1](2023-10-14T22:01:47.208088) 5초마다 실행
   */
//  @Scheduled(fixedRate = 5000, initialDelay = 3000)
//  public void cronTask() {
//    printLog("5초마다 실행");
//  }

  /*
  single thread와 동작이 동일하다. thread는 실행될 때마다 변경된다.

  [pool-2-thread-2](2023-10-14T22:02:50.983112) 5초 이후에 실행 - 시작
  [pool-2-thread-2](2023-10-14T22:02:58.988312) 5초 이후에 실행 - 종료
  [pool-2-thread-1](2023-10-14T22:02:58.989663) 5초 이후에 실행 - 시작
  [pool-2-thread-1](2023-10-14T22:03:06.994848) 5초 이후에 실행 - 종료
  [pool-2-thread-3](2023-10-14T22:03:06.995722) 5초 이후에 실행 - 시작
  [pool-2-thread-3](2023-10-14T22:03:15.000884) 5초 이후에 실행 - 종료
   */
  @Scheduled(fixedRate = 5000, initialDelay = 3000)
  public void longCronTask() throws InterruptedException {
    printLog("5초 이후에 실행 - 시작");
    Thread.sleep(8000);
    printLog("5초 이후에 실행 - 종료");
  }

  public void printLog(String message) {
    System.out.println("[" + Thread.currentThread().getName() + "](" + LocalDateTime.now() + ") " + message);
  }
}
