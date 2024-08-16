package study.spring.websocket.greetingwithkafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import study.spring.websocket.greetingwithkafka.kafka.KafkaConstant;

@Controller
public class GreetingController {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public GreetingController(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @MessageMapping("/hello")
  public void greeting(HelloMessage message) throws InterruptedException {
    // 시뮬레이션을 위한 딜레이
    Thread.sleep(1000);

    Greeting greeting = new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    kafkaTemplate.send(KafkaConstant.MESSAGE_TOPIC, greeting);
  }
}
