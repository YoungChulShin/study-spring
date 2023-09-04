package study.spring.delivery.adapter.out.persistentevent;

import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import study.spring.delivery.application.port.out.PersistentEventTransmitter;
import study.spring.delivery.domain.persistentevent.PersistentEvent;

@Slf4j
@Component
class PersistentEventKafkaTransmitter implements PersistentEventTransmitter {

  private final String topicName;
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final PersistentEventJpaRepository persistentEventRepository;

  public PersistentEventKafkaTransmitter(
      @Value("${persistent-event.transmitter.kafka.topic-name}") String topicName,
      KafkaTemplate<String, String> kafkaTemplate,
      PersistentEventJpaRepository persistentEventRepository) {
    this.topicName = topicName;
    this.kafkaTemplate = kafkaTemplate;
    this.persistentEventRepository = persistentEventRepository;
  }

  @Override
  public void transmit(PersistentEvent event) {
    CompletableFuture<SendResult<String, String>> future =
        kafkaTemplate.send(topicName, event.getEventId(), event.getBody());
    future.whenComplete((result, ex) -> {
      if (ex != null) {
        log.error("Persistent event 발행 후 에러 발생. eventId: {}", event.getEventId(), ex);
      } else {
        log.info("Persistent event 발행 완료. eventId: {}", event.getEventId());
        event.published();
        persistentEventRepository.save(event);
      }
    });
  }
}
