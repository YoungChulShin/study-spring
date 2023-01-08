package study.spring.food_delivery.presentation.model;

import lombok.Getter;

@Getter
public class GetAgentLocationResponse {

  private final Long id;
  private final Double longitude;
  private final Double latitude;

  public GetAgentLocationResponse(Long id, Double longitude, Double latitude) {
    this.id = id;
    this.longitude = longitude;
    this.latitude = latitude;
  }
}
