package study.spring.delivery.application.port.out;

import java.util.List;
import study.spring.delivery.domain.persistentevent.PersistentEvent;

public interface PersistentEventReader {

  List<PersistentEvent> findUnPublishedEvents();
}
