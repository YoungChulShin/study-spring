package study.spring.retry_transactional.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderReader orderReader;
  private final OrderStore orderStore;

  public OrderInfo getOrder(Long orderId) {
    Order order = orderReader.getOrder(orderId);
    return new OrderInfo(order);
  }

  public Long registerOrder(String orderNumber) {
    Order initOrder = new Order(orderNumber);
    Order order = orderStore.registerOrder(initOrder);

    return order.getId();
  }

  public OrderInfo updateOrderNumber(Long orderId, String orderNumber) {
    Order order = orderReader.getOrder(orderId);
    order.updateOrderNumber(orderNumber);

    return new OrderInfo(order);
  }
}
