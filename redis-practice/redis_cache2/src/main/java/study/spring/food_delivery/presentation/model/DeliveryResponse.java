package study.spring.food_delivery.presentation.model;

import lombok.Getter;

@Getter
public class DeliveryResponse {

  private final long deliverySum;

  public DeliveryResponse(long deliverySum) {
    this.deliverySum = deliverySum;
  }
}
