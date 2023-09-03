package study.spring.delivery.adapter.out.persistentevent;

import org.springframework.stereotype.Component;
import study.spring.delivery.application.port.out.PersistentEventTransmitter;
import study.spring.delivery.domain.persistentevent.PersistentEvent;

@Component
class PersistentEventKafkaTransmitter implements PersistentEventTransmitter {

  @Override
  public boolean transmit(PersistentEvent event) {
    return false;
  }
}
