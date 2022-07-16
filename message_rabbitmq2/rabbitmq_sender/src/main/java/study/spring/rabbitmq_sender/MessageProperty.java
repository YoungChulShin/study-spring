package study.spring.rabbitmq_sender;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "message")
@ConstructorBinding
public class MessageProperty {

  private final String queueName;
  private final String exchangeTopic;
  private final String routingKey;

  public MessageProperty(String queueName, String exchangeTopic, String routingKey) {
    this.queueName = queueName;
    this.exchangeTopic = exchangeTopic;
    this.routingKey = routingKey;
  }

  public String getQueueName() {
    return queueName;
  }

  public String getExchangeTopic() {
    return exchangeTopic;
  }

  public String getRoutingKey() {
    return routingKey;
  }
}
