package study.spring.delivery.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring.delivery.application.port.in.PersistentEventTransmitUseCase;
import study.spring.delivery.application.port.out.PersistentEventReader;
import study.spring.delivery.application.port.out.PersistentEventTransmitter;
import study.spring.delivery.domain.persistentevent.PersistentEvent;

@Service
@RequiredArgsConstructor
public class PersistentEventTransmitService implements PersistentEventTransmitUseCase {

  private final PersistentEventReader reader;
  private final PersistentEventTransmitter transmitter;

  @Override
  public void transmitPersistentEvents() {
    List<PersistentEvent> events = reader.findUnPublishedEvents();
    events.forEach(transmitter::transmit);
  }
}
