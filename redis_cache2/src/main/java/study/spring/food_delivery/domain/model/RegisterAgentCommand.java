package study.spring.food_delivery.domain.model;

import lombok.Getter;

@Getter
public class RegisterAgentCommand {

  private final String name;
  private final int age;

  public RegisterAgentCommand(String name, int age) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("name");
    }
    if (age <= 0) {
      throw new IllegalArgumentException("age");
    }

    this.name = name;
    this.age = age;
  }
}
