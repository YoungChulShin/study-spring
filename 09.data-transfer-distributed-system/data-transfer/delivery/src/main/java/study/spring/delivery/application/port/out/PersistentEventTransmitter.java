package study.spring.delivery.application.port.out;

import study.spring.delivery.domain.persistentevent.PersistentEvent;

public interface PersistentEventTransmitter {

  void transmit(PersistentEvent event);

}
