package study.spring.delivery.domain.delivery.model;

import lombok.Getter;

@Getter
public record CreateDeliveryCommand(
    String orderNumber,
    String sourceAddress,
    String destinationAddress) {

}
