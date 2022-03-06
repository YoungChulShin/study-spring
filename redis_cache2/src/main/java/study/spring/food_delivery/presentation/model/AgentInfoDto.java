package study.spring.food_delivery.presentation.model;

import lombok.Getter;
import study.spring.food_delivery.domain.model.AgentInfo;

@Getter
public class AgentInfoDto {

  private final Long id;
  private final String name;
  private final int age;
  private final int deliverySum;

  public AgentInfoDto(Long id, String name, int age, int deliverySum) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.deliverySum = deliverySum;
  }

  public static AgentInfoDto from(AgentInfo agentInfo) {
    return new AgentInfoDto(
        agentInfo.getId().getId(),
        agentInfo.getName(),
        agentInfo.getAge(),
        agentInfo.getDeliverySum());
  }
}
