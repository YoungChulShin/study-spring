package study.spring.food_delivery.domain.model;

import lombok.Getter;
import study.spring.food_delivery.domain.AgentLocation;

@Getter
public class AgentLocationInfo {

  private final Long id;
  private final Double longitude;
  private final Double latitude;

  public AgentLocationInfo(Long id, Double longitude, Double latitude) {
    this.id = id;
    this.longitude = longitude;
    this.latitude = latitude;
  }
}
