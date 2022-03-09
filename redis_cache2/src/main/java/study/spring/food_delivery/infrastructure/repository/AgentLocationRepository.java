package study.spring.food_delivery.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.food_delivery.domain.AgentLocation;

public interface AgentLocationRepository extends JpaRepository<AgentLocation, Long> {

}
