package study.spring.websocket.greetingwithkafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import study.spring.websocket.greetingwithkafka.Greeting;

@Component
public class KafkaMessageHandler {

  private final SimpMessagingTemplate messagingTemplate;

  public KafkaMessageHandler(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  @KafkaListener(
      topics = KafkaConstant.MESSAGE_TOPIC,
      groupId = "myGroup1"
  )
  public void handleMessage(ConsumerRecord<String, Greeting> record) {
    messagingTemplate.convertAndSend("/topic/greetings", record.value());

  }
}
