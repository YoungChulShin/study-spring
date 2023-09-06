package study.spring.websocket.chat.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import study.spring.websocket.chat.chat.ChatMessage;
import study.spring.websocket.chat.chat.MessageType;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

  private final SimpMessageSendingOperations messageTemplate;

  @EventListener
  public void handleWebSocketDisconnectedListener(SessionDisconnectEvent event) {
    // 로그아웃을 하면
    // 세션에서 username을 가지고 와서 (username은 컨트롤러에서 addUser를 할 때 세션에 추가한다)
    // '/topic/public'으로 LEAVE 타입의 메시지를 전송한다.
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
    String username = (String) headerAccessor.getSessionAttributes().get("username");
    if (username != null) {
      log.info("User disconnected: {}", username);
      var chatMessage = ChatMessage.builder()
          .type(MessageType.LEAVE)
          .sender(username)
          .build();
      messageTemplate.convertAndSend("/topic/public", chatMessage);
    }
  }
}
