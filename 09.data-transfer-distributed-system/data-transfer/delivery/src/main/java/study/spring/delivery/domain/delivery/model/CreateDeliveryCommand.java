package study.spring.delivery.domain.delivery.model;

import jakarta.validation.constraints.NotEmpty;

public record CreateDeliveryCommand(
    @NotEmpty String orderNumber,
    @NotEmpty String sourceAddress,
    @NotEmpty String destinationAddress) {

}
