package study.spring.delivery.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring.delivery.application.port.in.DeliveryUseCase;
import study.spring.delivery.application.port.out.DeliveryReader;
import study.spring.delivery.application.port.out.DeliveryWriter;
import study.spring.delivery.domain.delivery.Delivery;
import study.spring.delivery.domain.delivery.model.CreateDeliveryCommand;
import study.spring.delivery.domain.persistentevent.PersistentEventType;

@Service
@RequiredArgsConstructor
class DeliveryService implements DeliveryUseCase {

  private final DeliveryWriter deliveryWriter;
  private final DeliveryReader deliveryReader;
  private final PersistentEventCreateService persistentEventService;

  @Override
  @Transactional
  public Long create(CreateDeliveryCommand createRequest) {
    var delivery = new Delivery(createRequest);

    delivery = deliveryWriter.save(delivery);
    persistentEventService.create(PersistentEventType.DELIVERY_CREATED, delivery);

    return delivery.getId();
  }

  @Override
  @Transactional
  public void start(Long deliveryId) {
    Delivery delivery = deliveryReader.findById(deliveryId);
    if (delivery == null) {
      throw new RuntimeException("배송 정보를 찾을 수 없습니다");
    }

    delivery.start();
    persistentEventService.create(PersistentEventType.DELIVERY_STARTED, delivery);
  }

  @Override
  @Transactional
  public void complete(Long deliveryId) {
    Delivery delivery = deliveryReader.findById(deliveryId);
    if (delivery == null) {
      throw new RuntimeException("배송 정보를 찾을 수 없습니다");
    }

    delivery.complete();
    persistentEventService.create(PersistentEventType.DELIVERY_COMPLETED, delivery);
  }

  @Override
  @Transactional
  public void cancel(Long deliveryId) {
    Delivery delivery = deliveryReader.findById(deliveryId);
    if (delivery == null) {
      throw new RuntimeException("배송 정보를 찾을 수 없습니다");
    }

    delivery.cancel();
    persistentEventService.create(PersistentEventType.DELIVERY_CANCELLED, delivery);
  }
}
