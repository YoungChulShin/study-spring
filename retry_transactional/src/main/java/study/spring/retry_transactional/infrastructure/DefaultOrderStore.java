package study.spring.retry_transactional.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.spring.retry_transactional.domain.Order;
import study.spring.retry_transactional.domain.OrderStore;

@Component
@RequiredArgsConstructor
public class DefaultOrderStore implements OrderStore {

  private final OrderRepository orderRepository;

  @Override
  public Order registerOrder(Order order) {
    return orderRepository.save(order);
  }
}
