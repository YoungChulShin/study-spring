package study.spring.delivery.domain.delivery.model;

import lombok.Getter;

@Getter
public record CreateDeliveryCommand(
    Long orderId,
    String sourceAddress,
    String destinationAddress) {

}
