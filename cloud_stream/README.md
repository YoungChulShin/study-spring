## 모듈 설명
Spring Cloud Stream을 이용해서 메시지를 보내고 받는 테스트를 해봅니다.

## Message Broker
카프카를 사용

카프카 폴더에 Docker-Compose를 이용한 카프카 실행 및 스크립트 들이 포함 

## Message Sender
기능
- Post 요청으로 전달 받은 메시지를 카프카로 보내는 역할

클래스별 역할
- MessagingConfiguration: 바인딩 활성화
- ProducerChannel: Custom 메시지 채널 정의
- MessagePublisher: MessageChannel을 이용해서 메시지 전달
- LogMessage: 메시지 모델

환경 설정
- `test-message-stream` 토픽으로 메시지 바인딩
 
## Message Receiver(Consumer)
기능
- 카프카로 전달된 메시지를 수신하는 역할

클래스별 역할
- TestMessageConsumerChannel: 메시지 채널을 정의
- MessagingConfiguration: 바인딩 활성화
- TestEventConsumer: 이벤트를 실제로 소비. StreamListener 사용