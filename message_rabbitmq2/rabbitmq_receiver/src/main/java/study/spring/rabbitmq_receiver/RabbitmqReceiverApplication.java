package study.spring.rabbitmq_receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RabbitmqReceiverApplication {

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqReceiverApplication.class, args);
  }

}
