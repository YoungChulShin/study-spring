# 설명
'Transactional Outbox 패턴'을 이용해서 메시지를 전송하는 방법에 대해서 알아봅니다.

이 방법을 이용하면 메시지의 발행과 내부 비지니스 로직을 하나의 트랜잭션으로 묶어서 비니지스 로직이 완료되면 메시지 발행도 어떻게든 된다는 것을 보장할 수 있습니다.  

# 구현
## 구현 summary
![imeage](./images/summary.jpg)

간단한 '주문 서비스'와 '배송 서비스'가 있습니다. 
1. 주문 서비스는 클라이언트로부터 주문을 받습니다. 
2. 주문 서비스는 배송을 위해서 배송 서비스에 배송 요청을 합니다.
3. 배송 서비스는 배송을 진행합니다. 
4. 배송 서비스는 배송의 상태 변경(생성, 시작, 종료)를 메시지를 발행합니다. 
5. 주문 서비스는 배송 상태 변경을 메시지를 consume 하면서 확인할 수 있습니다.  

이번 구현에서는 그림에서 '파란색'으로 표시된 부분을 구현합니다. 
1. 배송 서비스는 배송 상태 변경을 처리합니다.
2. 배송 서비스는 배송 상태 변경을 메시지큐에 발행합니다. 

## 소스 코드 실행
1. docker-compose 실행
   1. `/docker` 경로로 이동합니다. 
   2. 아래 명령어를 실행합니다. 
      ```
      /docker/docker-compose up -d
      ```
2. kafka-topic 생성
   1. kafka binary가 없다면 다운로드 받습니다. (https://kafka.apache.org/downloads)
   2. `/bin` 경로로 이동해서 아래 명령어를 실행합니다. 
      ```
      // 토픽 생성
      ./kafka-topics.sh --create --bootstrap-server localhost:19092 --topic delivery-events
      ```
   3. 아래 명령어로 생성된 토픽을 조회합니다. 
      ```
      ./kafka-topics.sh --bootstrap-server localhost:19092 --list
      ```
3. 소스 코드를 실행합니다. 
4. Entry-Point
   - study.spring.delivery.adapter.in.api#DeliveryController

## Transactional Outbox 패턴 구현
### PersistentEvent 엔티티
Outbox 패턴을 구현하기 위해서는 이벤트 정보를 DB에 저장해야합니다. `PersistentEvent` 엔티티를 이용해서 이벤트 데이터를 저장합니다.

'DeliveryService' 클래스를 보면, 하나의 트랜잭션 메서드에서 도메인 메서드 호출 이후에 PersistentEvent를 저장하는 것을 볼 수 있습니다.  
```java
@Override
@Transactional
public Long create(CreateDeliveryCommand createRequest) {
    // 내부 로직 수행
    var delivery = new Delivery(createRequest);
    delivery = deliveryWriter.save(delivery);
    
    // PersistentEvent 생성 및 저장
    persistentEventService.create(PersistentEventType.DELIVERY_CREATED, delivery);

    return delivery.getId();
}
```

PersistentEvent 엔티티는 아래의 정보를 가집니다. 
- eventId: UUID의 이벤트 Id
- eventType: 이벤트 타입
- body: String 타입의 전송 이벤트 데이터
- createdAt: 생성 시간
- publishedAt: 발행 시간

### 이벤트 전송 및 콜백
이벤트 전송은 `study.spring.delivery.application.port.out.PersistentEventTransmitter` 인터페이스를 통해서 전송합니다.

인터페이스의 구현체는 outbound adapter에 있습니다. 현재 코드에서는 카프카를 이용하고 있습니다. 

카프카를 이용해서 이벤트 전송이 정상적으로 되었다면, 콜백 메서드에서 전송 결과를 업데이트합니다. 
```java
@Override
public void transmit(PersistentEvent event) {
    CompletableFuture<SendResult<String, String>> future =
        kafkaTemplate.send(topicName, event.getEventId(), event.getBody());
    future.whenComplete((result, ex) -> {
      if (ex != null) {
        log.error("Persistent event 발행 후 에러 발생. eventId: {}", event.getEventId(), ex);
      } else {
        log.info("Persistent event 발행 완료. eventId: {}", event.getEventId());
        event.published();
        persistentEventRepository.save(event);
      }
    });
}
```

