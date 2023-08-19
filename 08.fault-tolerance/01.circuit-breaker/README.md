# 설명
스프링에서 서킷브레이커를 구현 해봅니다. 

# resillience4j를 이용한 구현
## 참고 자료
- springboot3 resillience4j reference: https://resilience4j.readme.io/docs/getting-started-3
- resillience4j reference: https://resilience4j.readme.io/v0.17.0/docs/circuitbreaker#consume-emitted-circuitbreakerevents

## 코드 동작 흐름
`my-service`와 `uuid-service` 2개가 있습니다. 
- my-service는 uuid-service를 호출해서 UUID 정보를 얻습니다. 
- 사용자는 my-service의 `POST - api/v1/ids` 호출해서 ID를 생성합니다. 

서킷브레이커는 my-service에서 uuid-service를 호출하는 infra 레이어 클래스 `ExternalUUIDGenerator`에 있습니다. 

```
user -----> my-service ---(circuit braker)---> uuid-service
```

## resillience4j 설정
### application.yaml을 이용한 설정 작업
서킷브레이커 설정은 application.yaml과 code에서 모두 설정 가능합니다. 이 예제에서 application.yaml을 이용한 이유는 설정 값에 변경 사항이 생겼을 때, 코드를 수정하는게 아니라 설정 정보를 변경해서 대응하는게 더 좋을것 같다고 생각했습니다. 

코드에서 설정한 설정 정보는 아래와 같습니다. 
```yaml
resilience4j:
  circuitbreaker:
    configs:
      # @see https://resilience4j.readme.io/docs/circuitbreaker#create-and-configure-a-circuitbreaker
      default:
        failureRateThreshold: 50                  # 실패율이 50%를 넘어가면 Close -> Open으로 변경된다.
        slowCallRateThreshold: 100                # Slow call의 비율이 100%라면 Close -> Open으로 변경된다.
        slowCallDurationThreshold: 60000          # 1분 이상의 시간이 걸리는것을 show call로 판단한다.
        slidingWindowType: COUNT_BASED            # 갯수 기반 또는 시간(TIME_BASED)중에 선택할 수 있다
        slidingWindowSize: 10                     # COUNT_BASED면 갯수, TIME_BASED라면 초
        minimumNumberOfCalls: 10                  # 서킷브레이커가 동작하기 위해서는 최소한 10개의 요청을 받아야한다. 9개까지는 모두가 실패해도 회로가 차단되지 않는다.
        waitDurationInOpenState: 20000            # Open 상태에서는 10초 대기 후에 다시 Half Open으로 변경된다
        permittedNumberOfCallsInHalfOpenState: 10
    instances:
      # CircuitBreakerConfigCustomizer 를 이용해서 코드에서 override 할 수 있다.
      uuidService:
        baseConfig: default                       # default 설정을 기본으로 가져옵니다.
        failureRateThreshold: 60                  # failureRateThreshold를 50에서 60으로 변경합니다. 
```

### 서킷브레이커 값 변경
`minimumNumberOfCalls` 값 만큼 호출정보가 생기면 그때부터 서킷브레이커가 동작합니다. 

상태 변경 예시
1. CLOSED에서 시작
2. minimumNumberOfCalls 만큼 호출이 되면 서킷을 열지/닫을지 판단 진행
3. failureRateThreshold를 넘었다면 OPEN으로 변경
4. OPEN 상태에서는 fallback 메서드가 동작
5. waitDurationInOpenState 만큼의 시간이 지나면 HALF-OPEN 상태로 변경
   - 자동으로 변경되는것은 아니고 waitDurationInOpenState 시간 이후에 요청이 들어오면 그때 변경된다
6. HALF-OPEN 상태에서 permittedNumberOfCallsInHalfOpenState 만큼 요청이 들어오면 Threshold를 체크한다. 결과에 따라 다시 OPEN으로 가거나, CLOSED로 가게된다. 
7. 각 상태가 변경될 때 slidingWindows는 초기화 되는것 같다. (= 이전 상태에서의 slidingWindowSize 값이 새 상태에서 영향을 주지 않는것 같다.)

### 서킷브레이커 적용
적용하고자하는 메서드에 `@CircuitBreaker` 애노테이션을 선언하면 적용된다. 

```java
@CircuitBreaker(
      name = "uuidService", // 서킷브레이커 이름
      fallbackMethod = "fallback")  // fallback 메서드 이름
  @Override
  public String generateUUID() { 
    // 생략
  }
```

### fallback 메서드
fallback 메서드는 요청이 실패하거나 서킷이 OPEN 상태일 때, 값을 응답하는 메서드이다. 

규칙이 있는데, 아래와 같다.
1. 서킷브레이커가 적용된 메서드와 동일한 구조로 생성한다. 
2. 파라미터에 `Exception`을 추가한다. 

앞에서 공유한 `generateUUID()` 메서드의 fallback 메서드는 아래와 같이 만들 수 있다. 
```java
private String fallback(Exception e) { }
```

### 서킷브레이커 인스턴스 가져오기
application.yaml에서 서킷브레이커를 정의하고, 값을 세팅했다. 그런데 이번 제목처럼 이벤트를 적용하거나 기타 부가적인 작업을 해야할 경우가 있다. 

이때는 서킷브레이커 인스턴스에 접근해서 작업을 해야한다. `CircuitBreakerRegistry`를 이용해서 인스턴스에 접근할 수 있다. 
- CircuitBreakerRegistry는 의존성 주입을 받을 수 있다.
```java
// uuidService는 서킷브레이커 이름이다.
CircuitBreaker circuitBreaker = registry.find("uuidService").orElse(null);
```

### 이벤트 적용
CircuitBreaker 인스턴스에 접근하는 법을 알았다면, 인스턴스에 이벤트에 대한 코드를 추가할 수 있다. 

여기서는 서킷브레이커 상태가 변경될 때, 로그를 기록하는 코드를 추가했다. 
```java
CircuitBreaker circuitBreaker = registry.find("uuidService").orElse(null);

circuitBreaker.getEventPublisher()
    .onStateTransition(event -> {   // onStateTransition은 상태가 변경될 때 이벤트가 발행된다.
        // 발행된 이벤트의 정보를 로그로 기록한다.
        logger.info(event.getCircuitBreakerName() + " : " + event.getStateTransition());
    });
```