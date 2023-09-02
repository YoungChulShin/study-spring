package study.spring.delivery.application.port.out;

import study.spring.delivery.domain.persistentevent.PersistentEvent;

public interface PersistentEventWriter {

  PersistentEvent save(PersistentEvent persistentEvent);
}
