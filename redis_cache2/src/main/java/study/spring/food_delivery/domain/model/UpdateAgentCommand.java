package study.spring.food_delivery.domain.model;

import lombok.Getter;
import study.spring.food_delivery.domain.Agent.AgentId;

@Getter
public class UpdateAgentCommand {

  private final AgentId id;
  private final String name;
  private final Integer age;

  public UpdateAgentCommand(AgentId id, String name, Integer age) {
    if (id == null) {
      throw new IllegalArgumentException("id");
    }
    if (age != null && age <= 0) {
      throw new IllegalArgumentException("age");
    }
    if ((name == null || name.trim().length() == 0) && age == null) {
      throw new IllegalArgumentException("name, age");
    }

    this.id = id;
    this.name = (name != null) ? name.trim() : null;
    this.age = age;
  }
}
