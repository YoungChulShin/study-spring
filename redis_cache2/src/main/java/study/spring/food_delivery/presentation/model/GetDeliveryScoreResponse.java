package study.spring.food_delivery.presentation.model;

import java.util.Set;
import lombok.Getter;

@Getter
public class GetDeliveryScoreResponse {

  private final Set<String> agents;

  public GetDeliveryScoreResponse(Set<String> agents) {
    this.agents = agents;
  }
}
