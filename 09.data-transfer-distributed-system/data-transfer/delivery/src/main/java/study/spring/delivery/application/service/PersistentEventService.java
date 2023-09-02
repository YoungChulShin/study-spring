package study.spring.delivery.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring.delivery.application.port.out.PersistentEventWriter;
import study.spring.delivery.application.service.mapper.PersistentEventMapper;
import study.spring.delivery.domain.delivery.Delivery;
import study.spring.delivery.domain.persistentevent.PersistentEvent;
import study.spring.delivery.domain.persistentevent.PersistentEventType;

@Service
@RequiredArgsConstructor
public class PersistentEventService {

  private final PersistentEventMapper mapper;
  private final PersistentEventWriter writer;

  public PersistentEvent create(PersistentEventType eventType, Delivery delivery) {
    PersistentEvent entity = mapper.toEntity(eventType, delivery);
    return writer.save(entity);
  }

}
