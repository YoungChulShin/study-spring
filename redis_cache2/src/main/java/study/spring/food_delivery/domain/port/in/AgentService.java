package study.spring.food_delivery.domain.port.in;

import study.spring.food_delivery.domain.model.AgentLocationInfo;
import study.spring.food_delivery.domain.model.UpdateAgentCommand;
import study.spring.food_delivery.domain.model.AgentInfo;
import study.spring.food_delivery.domain.model.RegisterAgentCommand;
import study.spring.food_delivery.domain.model.UpdateAgentLocationCommand;

public interface AgentService {

  Long registerAgent(RegisterAgentCommand command);

  AgentInfo getAgentInfo(Long agentId);

  AgentInfo updateAgent(UpdateAgentCommand command);

  long delivery(Long agentId);

  Long updateAgentLocation(UpdateAgentLocationCommand command);

  AgentLocationInfo getAgentLocation(Long agentId);

}
