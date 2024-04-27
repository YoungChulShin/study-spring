package study.spring.websocket.websockettest;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
    String sessionId = headerAccessor.getSessionId();
    System.out.println("Session ID: " + sessionId);

    if (message.getName().contains("error")) {
      throw new IllegalArgumentException("Invalid message");
    }
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
  }

  @MessageExceptionHandler
  @SendToUser("/queue/errors")
  public String handleException(Throwable ex) {
    return ex.getMessage();
  }

}
