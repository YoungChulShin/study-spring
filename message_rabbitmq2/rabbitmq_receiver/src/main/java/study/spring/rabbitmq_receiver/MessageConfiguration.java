package study.spring.rabbitmq_receiver;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

  private final MessageProperty messageProperty;

  public MessageConfiguration(MessageProperty messageProperty) {
    this.messageProperty = messageProperty;
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

  @Bean
  MessageListenerAdapter messageListenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(
        receiver,
        "receiveMessage");
  }

  @Bean
  SimpleMessageListenerContainer container (
      ConnectionFactory connectionFactory,
      MessageListenerAdapter messageListenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addQueueNames(messageProperty.getQueueName());
    container.setMessageListener(messageListenerAdapter);

    return container;
  }
}
