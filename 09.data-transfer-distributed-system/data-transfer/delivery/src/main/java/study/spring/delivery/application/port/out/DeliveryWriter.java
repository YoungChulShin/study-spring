package study.spring.delivery.application.port.out;

import study.spring.delivery.domain.delivery.Delivery;

public interface DeliveryWriter {

  Delivery save(Delivery delivery);
}
