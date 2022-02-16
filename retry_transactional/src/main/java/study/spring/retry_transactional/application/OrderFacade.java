package study.spring.retry_transactional.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.retry_transactional.domain.OrderInfo;
import study.spring.retry_transactional.domain.OrderService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderFacade {

  private final OrderService orderService;

  @Transactional
  public Long registerOrder(String orderNumber) {
    return orderService.registerOrder(orderNumber);
  }

  public OrderInfo getOrder(Long orderId) {
    return orderService.getOrder(orderId);
  }

  @Transactional
  public OrderInfo updateOrderNumber(Long orderId, String orderNumber) {
    return orderService.updateOrderNumber(orderId, orderNumber);
  }
}
