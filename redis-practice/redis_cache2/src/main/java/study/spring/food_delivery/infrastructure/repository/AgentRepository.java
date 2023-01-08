package study.spring.food_delivery.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.food_delivery.domain.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {

}
