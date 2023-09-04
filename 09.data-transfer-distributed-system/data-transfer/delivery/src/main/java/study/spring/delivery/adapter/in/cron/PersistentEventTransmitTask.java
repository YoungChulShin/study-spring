package study.spring.delivery.adapter.in.cron;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import study.spring.delivery.application.port.in.PersistentEventTransmitUseCase;

@Component
@RequiredArgsConstructor
class PersistentEventTransmitTask {

  private final PersistentEventTransmitUseCase persistentEventTransmitUseCase;

  @Scheduled(cron = "0 * * * * *")
  void transmitPersistentEvents() {
    persistentEventTransmitUseCase.transmitPersistentEvents();
  }

}
