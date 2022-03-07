package study.spring.food_delivery.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.spring.food_delivery.domain.Agent;
import study.spring.food_delivery.domain.port.out.AgentStore;
import study.spring.food_delivery.infrastructure.repository.AgentRepository;

@Component
@RequiredArgsConstructor
public class AgentStoreImpl implements AgentStore {

  private final AgentRepository agentRepository;

  @Override
  public Agent saveAgent(Agent agent) {
    return agentRepository.save(agent);
  }
}