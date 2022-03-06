package study.spring.food_delivery.domain.model;

import lombok.Getter;
import study.spring.food_delivery.domain.Agent.AgentId;
import study.spring.food_delivery.domain.AgentLocation;

@Getter
public class UpdateAgentLocationCommand {

  private final AgentId id;
  private final AgentLocation location;

  public UpdateAgentLocationCommand(AgentId id, AgentLocation location) {
    if (id == null) {
      throw new IllegalArgumentException("id");
    }
    if (location == null) {
      throw new IllegalArgumentException("location");
    }
    if (location.getLatitude() == null || location.getLongitude() == null) {
      throw new IllegalArgumentException("location");
    }

    this.id = id;
    this.location = location;
  }
}
