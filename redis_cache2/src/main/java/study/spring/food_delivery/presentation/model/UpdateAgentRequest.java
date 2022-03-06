package study.spring.food_delivery.presentation.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateAgentRequest {

  @NotBlank
  private final String name;
  @Min(1)
  private final Integer age;

  public UpdateAgentRequest(String name, Integer age) {
    this.name = name;
    this.age = age;
  }
}
