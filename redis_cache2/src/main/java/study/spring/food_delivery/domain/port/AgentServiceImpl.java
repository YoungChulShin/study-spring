package study.spring.food_delivery.domain.port;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.food_delivery.domain.Agent;
import study.spring.food_delivery.domain.AgentLocation;
import study.spring.food_delivery.domain.model.AgentInfo;
import study.spring.food_delivery.domain.model.AgentLocationInfo;
import study.spring.food_delivery.domain.model.RegisterAgentCommand;
import study.spring.food_delivery.domain.model.UpdateAgentCommand;
import study.spring.food_delivery.domain.model.UpdateAgentLocationCommand;
import study.spring.food_delivery.domain.port.in.AgentService;
import study.spring.food_delivery.domain.port.out.AgentReader;
import study.spring.food_delivery.domain.port.out.AgentStore;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

  private final AgentStore agentStore;
  private final AgentReader agentReader;

  @Override
  @Transactional
  public Long registerAgent(RegisterAgentCommand command) {
    Agent initAgent = new Agent(command.getName(), command.getAge());
    Agent agent = agentStore.saveAgent(initAgent);

    return agent.getId();
  }

  @Override
  @Transactional(readOnly = true)
  public AgentInfo getAgentInfo(Long agentId) {
    Agent agent = agentReader.getAgent(agentId);

    return AgentInfo.from(agent);
  }

  @Override
  @Transactional
  public AgentInfo updateAgent(UpdateAgentCommand command) {
    Agent agent = agentReader.getAgent(command.getId());

    if (command.getName() != null && command.getName().length() > 0) {
      agent.updateName(command.getName());
    }
    if (command.getAge() != null) {
      agent.updateAge(command.getAge());
    }

    return AgentInfo.from(agent);
  }

  @Override
  @Transactional
  public long delivery(Long agentId) {
    Agent agent = agentReader.getAgent(agentId);

    return agent.delivery();
  }

  @Override
  @Transactional
  public Long updateAgentLocation(UpdateAgentLocationCommand command) {
    Agent agent = agentReader.getAgent(command.getId());
    agent.updateLocation(
        command.getLocation().getLongitude(),
        command.getLocation().getLatitude());

    agentStore.saveAgentLocationToCache(
        command.getId(),
        agent.getLocation());

    return agent.getId();
  }

  @Override
  @Transactional(readOnly = true)
  public AgentLocationInfo getAgentLocation(Long agentId) {
    AgentLocation agentLocation = agentReader.getAgentLocation(agentId);
    return new AgentLocationInfo(
        agentId,
        agentLocation.getLongitude(),
        agentLocation.getLatitude());
  }
}
