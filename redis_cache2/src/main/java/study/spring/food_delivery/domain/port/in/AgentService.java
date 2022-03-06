package study.spring.food_delivery.domain.port.in;

import study.spring.food_delivery.domain.Agent.AgentId;
import study.spring.food_delivery.domain.model.UpdateAgentCommand;
import study.spring.food_delivery.domain.model.AgentInfo;
import study.spring.food_delivery.domain.model.RegisterAgentCommand;
import study.spring.food_delivery.domain.model.UpdateAgentLocationCommand;

public interface AgentService {

  AgentId registerAgent(RegisterAgentCommand command);

  AgentInfo getAgentInfo(AgentId agentId);

  AgentInfo updateAgent(UpdateAgentCommand command);

  long delivery(AgentId agentId);

  AgentId updateAgentLocation(UpdateAgentLocationCommand command);
}
