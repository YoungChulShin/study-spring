package study.spring.food_delivery.presentation.model;

import lombok.Getter;

@Getter
public class UpdateAgentLocationResponse {

  private final Long id;

  public UpdateAgentLocationResponse(Long id) {
    this.id = id;
  }
}
