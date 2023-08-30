package study.spring.delivery.application.service;

import org.springframework.stereotype.Service;
import study.spring.delivery.application.port.in.DeliveryUseCase;
import study.spring.delivery.domain.Delivery;
import study.spring.delivery.domain.model.CreateDeliveryCommand;

@Service
class DeliveryService implements DeliveryUseCase {

  @Override
  public Long create(CreateDeliveryCommand createRequest) {
    var delivery = new Delivery(createRequest);
    // db save
    // 이벤트 발행
    return delivery.getId();
  }

  @Override
  public void start(Long deliveryId) {

  }

  @Override
  public void complete(Long deliveryId) {

  }

  @Override
  public void cancel(Long deliveryId) {

  }
}
