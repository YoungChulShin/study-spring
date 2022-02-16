package study.spring.retry_transactional.domain;

public interface OrderStore {

  Order registerOrder(Order order);
}
