package study.spring.delivery.adapter.in.api.model;

import jakarta.validation.constraints.NotEmpty;

public class DeliveryApiDto {

  public record CreateDeliveryRequest(
      @NotEmpty String orderNumber,
      @NotEmpty String sourceAddress,
      @NotEmpty String destinationAddress) {

  }
}
