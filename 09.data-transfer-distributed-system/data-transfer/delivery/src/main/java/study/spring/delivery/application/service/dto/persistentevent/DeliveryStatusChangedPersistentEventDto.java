package study.spring.delivery.application.service.dto.persistentevent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import study.spring.delivery.domain.delivery.Delivery;
import study.spring.delivery.domain.delivery.DeliveryStatus;
import study.spring.delivery.domain.persistentevent.PersistentEventType;

@Getter
public class DeliveryStatusChangedPersistentEventDto extends BasePersistentEventDto {

  @JsonProperty("body")
  private final DeliveryStatusChangedPersistentEventBodyDto body;

  public DeliveryStatusChangedPersistentEventDto(PersistentEventType eventType, Delivery delivery) {
    super(eventType);
    this.body = new DeliveryStatusChangedPersistentEventBodyDto(delivery);
  }

  @Getter
  private static class DeliveryStatusChangedPersistentEventBodyDto {

    private final String deliveryNumber;
    private final String orderNumber;
    private final DeliveryStatus deliveryStatus;

    public DeliveryStatusChangedPersistentEventBodyDto(Delivery delivery) {
      this.deliveryNumber = delivery.getDeliveryNumber();
      this.orderNumber = delivery.getOrderNumber();
      this.deliveryStatus = delivery.getDeliveryStatus();
    }
  }
}
