package study.spring.food_delivery.domain.model;

import lombok.Getter;
import study.spring.food_delivery.domain.Agent.AgentId;

@Getter
public class UpdateAgentCommand {

  private final AgentId id;
  private final String name;
  private final int age;

  public UpdateAgentCommand(AgentId id, String name, int age) {
    if (id == null) {
      throw new IllegalArgumentException("id");
    }
    if ((name == null || name.length() == 0) && age <= 0) {
      throw new IllegalArgumentException("name, age");
    }

    this.id = id;
    this.name = name;
    this.age = age;
  }
}
