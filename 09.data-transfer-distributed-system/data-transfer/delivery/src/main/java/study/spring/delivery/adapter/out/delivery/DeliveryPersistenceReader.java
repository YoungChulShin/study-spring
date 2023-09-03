package study.spring.delivery.adapter.out.delivery;

import org.springframework.stereotype.Component;
import study.spring.delivery.application.port.out.DeliveryReader;
import study.spring.delivery.domain.delivery.Delivery;

@Component
class DeliveryPersistenceReader implements DeliveryReader {

  private final DeliveryJpaRepository repository;

  public DeliveryPersistenceReader(DeliveryJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public Delivery findById(Long id) {
    return repository.findById(id).orElse(null);
  }
}
