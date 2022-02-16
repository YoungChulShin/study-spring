package study.spring.retry_transactional.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.spring.retry_transactional.domain.Order;
import study.spring.retry_transactional.domain.OrderReader;

@Component
@RequiredArgsConstructor
public class DefaultOrderReader implements OrderReader {

  private final OrderRepository orderRepository;

  @Override
  public Order getOrder(Long orderId) {
    return orderRepository.findOne(orderId);
  }
}
