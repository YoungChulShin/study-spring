package study.spring.delivery.adapter.in.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.delivery.adapter.in.api.model.DeliveryApiDto;
import study.spring.delivery.application.port.in.DeliveryUseCase;
import study.spring.delivery.domain.delivery.model.CreateDeliveryCommand;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
class DeliveryController {

  private final DeliveryUseCase deliveryUseCase;

  @PostMapping
  public Long createDelivery(@RequestBody @Valid DeliveryApiDto.CreateDeliveryRequest request) {
    return deliveryUseCase.create(new CreateDeliveryCommand(
        request.orderNumber(), request.sourceAddress(), request.destinationAddress()));
  }

  @PostMapping("/{deliveryId}/start")
  public void startDelivery(@PathVariable Long deliveryId) {
    deliveryUseCase.start(deliveryId);
  }

  @PostMapping("/{deliveryId}/complete")
  public void completeDelivery(@PathVariable Long deliveryId) {
    deliveryUseCase.complete(deliveryId);
  }

  @PostMapping("/{deliveryId}/cancel")
  public void cancelDelivery(@PathVariable Long deliveryId) {
    deliveryUseCase.cancel(deliveryId);
  }

}
