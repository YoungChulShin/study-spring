package study.spring.food_delivery.presentation.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterAgentRequest {

  @NotBlank
  private final String name;
  @Min(1)
  private final int age;

  public RegisterAgentRequest(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
