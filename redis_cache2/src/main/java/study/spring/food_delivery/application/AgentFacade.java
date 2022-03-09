package study.spring.food_delivery.application;

import lombok.RequiredArgsConstructor;
import study.spring.food_delivery.common.Facade;
import study.spring.food_delivery.domain.model.AgentInfo;
import study.spring.food_delivery.domain.model.AgentLocationInfo;
import study.spring.food_delivery.domain.model.RegisterAgentCommand;
import study.spring.food_delivery.domain.model.UpdateAgentCommand;
import study.spring.food_delivery.domain.model.UpdateAgentLocationCommand;
import study.spring.food_delivery.domain.port.in.AgentService;

@Facade
@RequiredArgsConstructor
public class AgentFacade {

  private final AgentService agentService;

  public Long registerAgent(RegisterAgentCommand command) {
    return agentService.registerAgent(command);
  }

  public AgentInfo getAgentInfo(Long agentId) {
    return agentService.getAgentInfo(agentId);
  }

  public AgentInfo updateAgent(UpdateAgentCommand command) {
    return agentService.updateAgent(command);
  }

  public long delivery(Long agentId) {
    return agentService.delivery(agentId);
  }

  public Long updateAgentLocation(UpdateAgentLocationCommand command) {
    return agentService.updateAgentLocation(command);
  }

  public AgentLocationInfo getAgentLocation(Long agentId) {
    return agentService.getAgentLocation(agentId);
  }
}
