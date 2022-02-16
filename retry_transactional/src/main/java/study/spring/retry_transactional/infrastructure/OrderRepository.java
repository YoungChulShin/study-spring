package study.spring.retry_transactional.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.retry_transactional.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
