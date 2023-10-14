# 저장소 설명
스프링에서 scheduler를 사용하는 방법을 알아봅니다. 

하위 프로젝트
- single-thread: single thread에서 cron 동작 프로젝트 
- multi-thread: multi thread에서 cron 동작 프로젝트

# Scheduler를 사용
## Scheduler 설정
Scheduler 설정 방법
```java
// @EnableScheduling 애노테이션을 추가한다
@Configuration
@EnableScheduling
public class SchedulerConfig {

}
```
## @Scheduled 옵션 별 동작
### cron
cron expression에 맞게 작업이 시작된다. cron 값은 '초, 분, 시, 일, 월, 요일'로 설정할 수 있다. 

```java
@Scheduled(cron = "*/5 * * * * *")
public void cronTask() {
    printLog("5초마다 실행");
}
```

아래처럼 Task가 5초보다 오래 걸린다면, 해당 시간에 cron 작업은 진행되지 않는다. 
```java
/*
[scheduling-1](2023-10-11T23:02:40.004294) 5초마다 실행 - 시작
[scheduling-1](2023-10-11T23:02:48.009778) 5초마다 실행 - 종료
[scheduling-1](2023-10-11T23:02:50.001792) 5초마다 실행 - 시작
[scheduling-1](2023-10-11T23:02:58.007077) 5초마다 실행 - 종료
[scheduling-1](2023-10-11T23:03:00.007735) 5초마다 실행 - 시작
[scheduling-1](2023-10-11T23:03:08.012863) 5초마다 실행 - 종료
*/
@Scheduled(cron = "*/5 * * * * *")
public void longCronTask() throws InterruptedException {
    printLog("5초마다 실행 - 시작");
    Thread.sleep(8000);
    printLog("5초마다 실행 - 종료");
}
```

### fixedDelay
작업이 종료되고 다음 작업이 시작될 때까지 `fixedDelay`로 설정된 시간을 대기한다. 

```java
@Scheduled(fixedDelay = 5000, initialDelay = 3000)
public void cronTask() {
    printLog("5초 이후에 실행");
}
```

### fixedRate
작업이 `fixedRate` 주기에 따라서 실행된다.

```java
/*
[scheduling-1](2023-10-14T21:30:17.949768) 5초마다 실행
[scheduling-1](2023-10-14T21:30:22.948420) 5초마다 실행
[scheduling-1](2023-10-14T21:30:27.944368) 5초마다 실행
*/
@Scheduled(fixedRate = 5000, initialDelay = 3000)
public void cronTask() {
    printLog("5초마다 실행");
}
```

작업이 fixedRate보다 오래 걸린다면, 다음 작업은 마지막 작업이 종료되고 바로 실행된다. 
```java
/*
[scheduling-1](2023-10-14T21:22:57.986915) 5초 이후에 실행 - 시작
[scheduling-1](2023-10-14T21:23:05.988736) 5초 이후에 실행 - 종료
[scheduling-1](2023-10-14T21:23:05.989212) 5초 이후에 실행 - 시작
[scheduling-1](2023-10-14T21:23:13.993574) 5초 이후에 실행 - 종료
[scheduling-1](2023-10-14T21:23:13.993702) 5초 이후에 실행 - 시작
[scheduling-1](2023-10-14T21:23:21.998843) 5초 이후에 실행 - 종료
*/
@Scheduled(fixedRate = 5000, initialDelay = 3000)
public void longCronTask() throws InterruptedException {
    printLog("5초 이후에 실행 - 시작");
    Thread.sleep(8000);
    printLog("5초 이후에 실행 - 종료");
}
```

## SingleThread와 MultiThread에서 cron 동작
기본적으로 schulduler는 singleThread로 동작한다. 하지만 `SchedulingConfigurer` 인터페이스를 구현해서 스케줄러 설정을 해줄 수 있다. 

__SingleThread와 MultiThread에서 중요한 공통점이 있는데, 1개의 Task는 동시에 실행이 안된다는 점이다. 즉, 특정 Task가 반복 주기를 넘어선다고해서 새로운 Thread에서 실행되는 것은 아니다.__

## MultiThread 설정
`SchedulingConfigurer`를 구현한다. 

```java
@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.setScheduler(taskScheduler());
  }

  // Spring application이 종료될 땨 task executor가 종료되는 것을 보장하기 위함
  @Bean(destroyMethod="shutdown")
  public Executor taskScheduler() {
    return Executors.newScheduledThreadPool(10);
  }
}
```