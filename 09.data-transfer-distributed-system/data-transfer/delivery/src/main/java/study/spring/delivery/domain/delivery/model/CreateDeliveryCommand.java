package study.spring.delivery.domain.delivery.model;

public record CreateDeliveryCommand(
    String orderNumber,
    String sourceAddress,
    String destinationAddress) {

}
