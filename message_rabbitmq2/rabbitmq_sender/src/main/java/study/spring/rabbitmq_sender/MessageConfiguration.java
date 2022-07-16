package study.spring.rabbitmq_sender;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

  private final MessageProperty messageProperty;

  public MessageConfiguration(MessageProperty property) {
    this.messageProperty = property;
  }

  @Bean
  Queue queue() {
    return new Queue(messageProperty.getQueueName(), false);
  }

  @Bean
  TopicExchange topicExchange() {
    return new TopicExchange(messageProperty.getExchangeTopic());
  }

  @Bean
  Binding binding(Queue queue, TopicExchange topicExchange) {
    return BindingBuilder
        .bind(queue)
        .to(topicExchange)
        .with(messageProperty.getRoutingKey());
  }
}
