package study.spring.rabbitmq_sender;

import java.time.LocalDateTime;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  private final MessageProperty property;

  public Runner(RabbitTemplate rabbitTemplate, MessageProperty property) {
    this.rabbitTemplate = rabbitTemplate;
    this.property = property;
  }

  @Override
  public void run(String... args) throws Exception {
    for (int i = 0; i < 100; i++) {
      sendMessage();

      Thread.sleep(1000);
    }
  }

  private void sendMessage() {
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend(
        property.getExchangeTopic(),
        "my.message.hello",
        LocalDateTime.now().toString());
  }


}
