package study.spring.food_delivery.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.spring.food_delivery.domain.Agent;
import study.spring.food_delivery.domain.Agent.AgentId;
import study.spring.food_delivery.domain.port.out.AgentReader;
import study.spring.food_delivery.infrastructure.repository.AgentRepository;

@Component
@RequiredArgsConstructor
public class AgentReaderImpl implements AgentReader {

  private final AgentRepository agentRepository;

  @Override
  public Agent getAgent(AgentId agentId) {
    return agentRepository.findById(agentId.getId())
        .orElseThrow(IllegalArgumentException::new);
  }
}
