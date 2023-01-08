package study.spring.food_delivery.infrastructure;

import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import study.spring.food_delivery.common.RedisKey;
import study.spring.food_delivery.domain.Agent;
import study.spring.food_delivery.domain.AgentLocation;
import study.spring.food_delivery.domain.port.out.AgentReader;
import study.spring.food_delivery.infrastructure.repository.AgentLocationRepository;
import study.spring.food_delivery.infrastructure.repository.AgentRepository;

@Component
@RequiredArgsConstructor
public class AgentReaderImpl implements AgentReader {

  private final AgentRepository agentRepository;
  private final AgentLocationRepository agentLocationRepository;
  private final RedisTemplate redisTemplate;

  @Override
  public Agent getAgent(Long agentId) {
    return agentRepository.findById(agentId)
        .orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public AgentLocation getAgentLocation(Long agentId) {
//    SetOperations<String, Double> setOperations = redisTemplate.opsForSet();
//    Set<Double> members = setOperations.members(KEY_AGENT_LOCATION + agentId);
//    if (members != null && !members.isEmpty()) {
//      Iterator<Double> iterator = members.iterator();
//      return new AgentLocation(iterator.next(), iterator.next());
//    }

    Optional<AgentLocation> dbAgentLocation = agentLocationRepository.findById(agentId);
    return dbAgentLocation.map(
        agentLocation -> new AgentLocation(
            agentLocation.getLongitude(),
            agentLocation.getLatitude())).
        orElseGet(() -> new AgentLocation(null, null));
  }

  @Override
  public Set<String> getTopNAgent(int n) {
    ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
    return zSetOperations.reverseRange(
        RedisKey.KEY_DELIVERY_COUNT,
        0,
        n - 1);
  }
}
