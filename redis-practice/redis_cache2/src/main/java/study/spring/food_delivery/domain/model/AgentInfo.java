package study.spring.food_delivery.domain.model;

import lombok.Getter;
import study.spring.food_delivery.domain.Agent;

@Getter
public class AgentInfo {

  private Long id;
  private String name;
  private int age;
  private int deliverySum;

  public AgentInfo(Long id, String name, int age, int deliverySum) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.deliverySum = deliverySum;
  }

  public static AgentInfo from(Agent agent) {
    return new AgentInfo(
        agent.getId(),
        agent.getName(),
        agent.getAge(),
        agent.getDeliverySum());
  }
}