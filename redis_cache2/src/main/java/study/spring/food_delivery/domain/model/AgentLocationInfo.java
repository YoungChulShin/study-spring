package study.spring.food_delivery.domain.model;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class AgentLocationInfo implements Serializable {

  private final Long id;
  private final Double longitude;
  private final Double latitude;

  public AgentLocationInfo(Long id, Double longitude, Double latitude) {
    this.id = id;
    this.longitude = longitude;
    this.latitude = latitude;
  }
}
