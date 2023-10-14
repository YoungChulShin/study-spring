package study.spring.singlethread.cron;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleScheduler {

  /*
  'cron' 설정을 하면 정해진 시간마다 cron 작업을 실행한다.
  cron 값은 '초, 분, 시, 일, 월, 요일'로 설정할 수 있다.

  아래 코드는 5초마다 작업을 실행한다.
  [scheduling-1](2023-10-11T22:57:05.005770) 5초마다 실행
  [scheduling-1](2023-10-11T22:57:10.001301) 5초마다 실행
  [scheduling-1](2023-10-11T22:57:15.005553) 5초마다 실행
  [scheduling-1](2023-10-11T22:57:20.003836) 5초마다 실행
  [scheduling-1](2023-10-11T22:57:25.000777) 5초마다 실행
   */
//  @Scheduled(cron = "*/5 * * * * *")
//  public void cronTask() {
//    printLog("5초마다 실행");
//  }

  /*
  cron은 별도로 executor를 설정하지 않으면 single-thread로 동작합니다.
  이 경우 진행중인 작업이 cron의 반복주기보다 길다면, 해당 반복주기는 무시됩니다.

  아래 코드는 5초마다 실행되는 cron에서, 8초가 걸리는 작업을 설정했습니다.
  이 경우 10초마다 실행되도록 동작하게 됩니다.

  [scheduling-1](2023-10-11T23:02:40.004294) 5초마다 실행 - 시작
  [scheduling-1](2023-10-11T23:02:48.009778) 5초마다 실행 - 종료
  [scheduling-1](2023-10-11T23:02:50.001792) 5초마다 실행 - 시작
  [scheduling-1](2023-10-11T23:02:58.007077) 5초마다 실행 - 종료
  [scheduling-1](2023-10-11T23:03:00.007735) 5초마다 실행 - 시작
  [scheduling-1](2023-10-11T23:03:08.012863) 5초마다 실행 - 종료
   */
//  @Scheduled(cron = "*/5 * * * * *")
//  public void longCronTask() throws InterruptedException {
//    printLog("5초마다 실행 - 시작");
//    Thread.sleep(8000);
//    printLog("5초마다 실행 - 종료");
//  }

  /*
  schedule 작업이 완료되면 'fixedDelay' 이후에 작업이 실행됩니다.
  'initialDelay'를 설정하면 시작 시간을 조정할 수 있습니다.

  [scheduling-1](2023-10-14T21:14:47.570956) 5초 이후에 실행
  [scheduling-1](2023-10-14T21:14:52.578105) 5초 이후에 실행
   */
//  @Scheduled(fixedDelay = 5000, initialDelay = 3000)
//  public void cronTask() {
//    printLog("5초 이후에 실행");
//  }

  /*
  fixedDelay는 마지막 완료시간 기준으로 'fixedDelay' 만큼의 지연 시간을 가지기 때문에
  longCronTask는 작업 시간이 길어지는 차이만 있고 기존 cronTask와 차이는 없습니다.

  [scheduling-1](2023-10-14T21:17:18.839338) 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T21:17:26.842420) 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T21:17:31.847928) 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T21:17:39.849477) 5초 이후에 실행 - 종료
   */
//  @Scheduled(fixedDelay = 5000, initialDelay = 3000)
//  public void longCronTask() throws InterruptedException {
//    printLog("5초 이후에 실행 - 시작");
//    Thread.sleep(8000);
//    printLog("5초 이후에 실행 - 종료");
//  }

  /*
  fixedRate는 일정한 시간에 메서드가 실행되는 것을 보장한다.
  fixedRate가 5000으로 설정되어있으면, 5초마다 실행되는 것을 의미한다.

  [scheduling-1](2023-10-14T21:30:17.949768) 5초마다 실행
  [scheduling-1](2023-10-14T21:30:22.948420) 5초마다 실행
  [scheduling-1](2023-10-14T21:30:27.944368) 5초마다 실행
   */
  @Scheduled(fixedRate = 5000, initialDelay = 3000)
  public void cronTask() {
    printLog("5초마다 실행");
  }

  /*
  fixedRate보다 작업의 실행시간이 긴 경우에는 마지막 메서드가 종료되면 바로 실행된다.

  [scheduling-1](2023-10-14T21:22:57.986915) 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T21:23:05.988736) 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T21:23:05.989212) 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T21:23:13.993574) 5초 이후에 실행 - 종료
  [scheduling-1](2023-10-14T21:23:13.993702) 5초 이후에 실행 - 시작
  [scheduling-1](2023-10-14T21:23:21.998843) 5초 이후에 실행 - 종료
   */
//  @Scheduled(fixedRate = 5000, initialDelay = 3000)
//  public void longCronTask() throws InterruptedException {
//    printLog("5초 이후에 실행 - 시작");
//    Thread.sleep(8000);
//    printLog("5초 이후에 실행 - 종료");
//  }

  public void printLog(String message) {
    System.out.println("[" + Thread.currentThread().getName() + "](" + LocalDateTime.now() + ") " + message);
  }

}
