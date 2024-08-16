package study.spring.websocket.greetingwithkafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageHandler {

  @KafkaListener(
      topics = KafkaConstant.MESSAGE_TOPIC,
      groupId = "myGroup1"
  )
  public void handleMessage(ConsumerRecord<String, Object> record) {
    String a = "aa";

  }
}
