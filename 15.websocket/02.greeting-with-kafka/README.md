# 저장소 설명
`01.greeting` 프로젝트에 Kafka를 연동해서 서버가 여러개로 늘어나도 처리 가능하도록 합니다.

# 연동 설명
## 연동 방법
Websocket을 통해서 들어온 메시지를 subscribe 중인 클라이언트에게 바로 메시지를 보내지 않고, 중간에 kafka를 이용해서 처리합니다. 이는 메시지를 보내는 사용자와 받는 사용자가 서로 다른 서버에 있을 때에도 대응이 가능하도록 합니다. 

## 메시지 연동 순서
1. 클라이언트가 웹소켓에 연결한다. 
2. 연결 이후에 `hello` 로 매시지를 보낸다. 
3. 메시지는 `GreetingController#greeting` 에서 처리한다. 
4. `GreetingController#greeting` 은 메시지를 확인하고, kafka 로 메시지를 보낸다. 
5. 서버가 여려개 있다고 할 때, kafka로 보내진 메시지는 각 서버에서 구독한다. 
6. `KafkaMessageHandler#handleMessage`에서는 발행된 kafka 메시지를 처리한다.
   - 수신된 메시지를 웹소켓을 구독하는 클라이언트가 알 수 있도록 전다해준다. 