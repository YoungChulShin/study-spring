package study.spring.retry_transactional.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderReader orderReader;
  private final OrderStore orderStore;

  public Order getOrder(Long orderId) {
    return orderReader.getOrder(orderId);
  }

  public Long createOrder(String orderNumber) {
    Order initOrder = new Order(orderNumber);
    Order order = orderStore.registerOrder(initOrder);

    return order.getId();
  }
}
