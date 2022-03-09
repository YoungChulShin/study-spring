package study.spring.food_delivery.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;
import study.spring.food_delivery.domain.Agent;
import study.spring.food_delivery.domain.AgentLocation;
import study.spring.food_delivery.domain.port.out.AgentStore;
import study.spring.food_delivery.infrastructure.repository.AgentRepository;

@Component
@RequiredArgsConstructor
public class AgentStoreImpl implements AgentStore {

  private static final String KEY_AGENT_LOCATION = "api:agent:location:";

  private final AgentRepository agentRepository;
  private final RedisTemplate redisTemplate;

  @Override
  public Agent saveAgent(Agent agent) {
    return agentRepository.save(agent);
  }

  @Override
  public void saveAgentLocationToCache(Long agentId, AgentLocation agentLocation) {
    SetOperations<String, Double> setOperations = redisTemplate.opsForSet();
    setOperations.add(
        KEY_AGENT_LOCATION + agentId,
        agentLocation.getLongitude(),
        agentLocation.getLatitude());
  }
}