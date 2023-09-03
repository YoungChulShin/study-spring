package study.spring.delivery.adapter.out.persistentevent;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.spring.delivery.application.port.out.PersistentEventReader;
import study.spring.delivery.domain.persistentevent.PersistentEvent;

@Component
@RequiredArgsConstructor
public class PersistentEventPersistenceReader implements PersistentEventReader {

  private final PersistentEventJpaRepository repository;

  @Override
  public List<PersistentEvent> findUnPublishedEvents() {
    return repository.findByPublishedAtIsNullOOrderById();
  }
}
