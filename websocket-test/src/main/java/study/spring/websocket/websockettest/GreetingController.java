package study.spring.websocket.websockettest;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GreetingController {

  private final SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/hello")
  public void greeting(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
    String sessionId = headerAccessor.getSessionId();
    System.out.println("Session ID: " + sessionId);

    if (!message.isValid()) {
      throw new IllegalArgumentException("Invalid message");
    }

    simpMessagingTemplate.convertAndSend(
        "/topic/greetings/" + message.getChannelId(),
        new Greeting(String.format("[Channel: %s] Hello, %s!", message.getChannelId(), message.getName())));
  }

  @MessageExceptionHandler
  @SendToUser("/queue/errors")
  public String handleException(Throwable ex) {
    return ex.getMessage();
  }

}
