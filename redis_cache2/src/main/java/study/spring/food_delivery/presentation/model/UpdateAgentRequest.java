package study.spring.food_delivery.presentation.model;

import lombok.Getter;

@Getter
public class UpdateAgentRequest {

  private final String name;
  private final Integer age;

  public UpdateAgentRequest(String name, Integer age) {
    this.name = name;
    this.age = age;
  }
}
