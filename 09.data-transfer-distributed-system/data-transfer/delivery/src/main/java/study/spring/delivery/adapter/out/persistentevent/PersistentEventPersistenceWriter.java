package study.spring.delivery.adapter.out.persistentevent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.spring.delivery.application.port.out.PersistentEventWriter;
import study.spring.delivery.domain.persistentevent.PersistentEvent;

@Component
@RequiredArgsConstructor
public class PersistentEventPersistenceWriter implements PersistentEventWriter {

  private final PersistentEventJpaRepository repository;

  @Override
  public PersistentEvent save(PersistentEvent persistentEvent) {
    return repository.save(persistentEvent);
  }
}
