package study.spring.delivery.adapter.out.persistence.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.delivery.domain.delivery.Delivery;

interface DeliveryJpaRepository extends JpaRepository<Delivery, Long> {

}
