package study.spring.food_delivery.presentation.model;

import lombok.Getter;

@Getter
public class RegisterAgentResponse {

  private final Long id;

  public RegisterAgentResponse(Long id) {
    this.id = id;
  }
}
