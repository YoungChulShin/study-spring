package study.spring.delivery.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring.delivery.application.port.out.PersistentEventWriter;
import study.spring.delivery.domain.delivery.Delivery;
import study.spring.delivery.domain.persistentevent.PersistentEvent;
import study.spring.delivery.domain.persistentevent.PersistentEventType;

@Service
@RequiredArgsConstructor
public class PersistentEventService {

  private final PersistentEventWriter persistentEventWriter;

  public PersistentEvent create(Delivery delivery, PersistentEventType eventType) {
    return null;
  }

}
