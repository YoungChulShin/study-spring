package study.spring.rabbitmq_receiver;

import org.springframework.stereotype.Component;

@Component
public class Receiver {

  public void receiveMessage(String message) {
    System.out.println("Receive: " + message);
  }

}
