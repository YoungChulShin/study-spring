package study.spring.retry_transactional.presentation;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring.retry_transactional.application.OrderFacade;
import study.spring.retry_transactional.domain.OrderInfo;

@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrderFacade orderFacade;

  @GetMapping("/api/orders/{orderId}")
  public OrderInfo getOrder(@PathVariable(name = "orderId") Long orderId) {
    return orderFacade.getOrder(orderId);
  }

  @PostMapping("/api/orders")
  public Long registerOrder(@Valid @RequestBody RegisterOrderRequest request) {
    return orderFacade.registerOrder(request.getOrderNumber());
  }
}
