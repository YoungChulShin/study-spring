package study.spring.retry_transactional.domain;

public interface OrderReader {

  Order getOrder(Long orderId);
}
