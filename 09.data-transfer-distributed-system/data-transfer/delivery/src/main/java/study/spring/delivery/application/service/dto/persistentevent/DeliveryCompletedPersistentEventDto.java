package study.spring.delivery.application.service.dto.persistentevent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import study.spring.delivery.domain.delivery.Delivery;
import study.spring.delivery.domain.delivery.DeliveryStatus;
import study.spring.delivery.domain.persistentevent.PersistentEventType;

@Getter
public class DeliveryCompletedPersistentEventDto extends BasePersistentEventDto {

  @JsonProperty("body")
  private final DeliveryCompletedPersistentEventBodyDto body;

  public DeliveryCompletedPersistentEventDto(Delivery delivery) {
    super(PersistentEventType.DELIVERY_COMPLETED);
    this.body = new DeliveryCompletedPersistentEventBodyDto(delivery);
  }

  @Getter
  private static class DeliveryCompletedPersistentEventBodyDto {

    private final String deliveryNumber;
    private final String orderNumber;
    private final DeliveryStatus deliveryStatus;

    public DeliveryCompletedPersistentEventBodyDto(Delivery delivery) {
      this.deliveryNumber = delivery.getDeliveryNumber();
      this.orderNumber = delivery.getOrderNumber();
      this.deliveryStatus = delivery.getDeliveryStatus();
    }
  }
}
