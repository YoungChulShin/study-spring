package study.spring.delivery.application.port.out;

import study.spring.delivery.domain.Delivery;

public interface DeliveryReader {

  Delivery findById(Long id);

}
