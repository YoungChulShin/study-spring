package study.spring.delivery.adapter.out.persistence;

import org.springframework.stereotype.Component;
import study.spring.delivery.application.port.out.DeliveryWriter;
import study.spring.delivery.domain.delivery.Delivery;

@Component
class DeliveryPersistenceWriter implements DeliveryWriter {

  private final DeliveryJpaRepository repository;

  public DeliveryPersistenceWriter(DeliveryJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public Delivery save(Delivery delivery) {
    return repository.save(delivery);
  }
}
