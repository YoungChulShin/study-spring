package study.spring.rabbitmq_sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RabbitmqSenderApplication {

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqSenderApplication.class, args);
  }

}
