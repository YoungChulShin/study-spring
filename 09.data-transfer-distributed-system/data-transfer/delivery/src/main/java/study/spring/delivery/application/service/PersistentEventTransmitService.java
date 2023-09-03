package study.spring.delivery.application.service;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;
import study.spring.delivery.application.port.in.PersistentEventTransmitUseCase;
import study.spring.delivery.application.port.out.PersistentEventReader;
import study.spring.delivery.application.port.out.PersistentEventTransmitter;
import study.spring.delivery.domain.persistentevent.PersistentEvent;

@Service
public class PersistentEventTransmitService implements PersistentEventTransmitUseCase {

  private final PersistentEventReader reader;
  private final PersistentEventTransmitter transmitter;

  @Override
  @Transactional
  public void transmitPersistentEvents() {
    List<PersistentEvent> events = reader.findUnPublishedEvents();
    events.forEach(event -> {
      boolean result = transmitter.transmit(event);
      if (result) {
        event.published();
      }
    });
  }
}
