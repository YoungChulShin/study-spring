package study.spring.delivery.adapter.out.persistence.persistentevent;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.delivery.domain.persistentevent.PersistentEvent;

public interface PersistentEventJpaRepository extends JpaRepository<PersistentEvent, Long> {

}
