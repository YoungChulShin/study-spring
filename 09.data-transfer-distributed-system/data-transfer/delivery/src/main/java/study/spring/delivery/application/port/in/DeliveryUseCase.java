package study.spring.delivery.application.port.in;

import study.spring.delivery.domain.model.CreateDeliveryCommand;

public interface DeliveryUseCase {

  Long create(CreateDeliveryCommand createRequest);

  void start(Long deliveryId);

  void complete(Long deliveryId);

  void cancel(Long deliveryId);
}
