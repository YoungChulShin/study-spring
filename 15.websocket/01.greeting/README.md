# 저장소 설명
웹소켓을 이용한 간단한 `Hello World` 애플리케이션을 만들어봅니다. 스프링 사이트에 있는 [가이드](https://spring.io/guides/gs/messaging-stomp-websocket)를 따라합니다. 

# 의존성 
org.springframework.boot:spring-boot-starter-websocket

# 프로젝트 설명
## 기능 설명
같은 페이지에 접속한 사용자들에게 인사 메시지를 보낼 수 있는 기능을 구현합니다.

## 웹소켓 정보
endpoint: `/gs-guide-websocket`

메시지 전송: `/app/hello`

메시지 구독: `/topic/greetings`

## 실행
서버 실행 이후에 `localhost:8080/index.html` 로 접속하면 UI 확인 가능합니다. 


