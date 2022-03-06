package study.spring.food_delivery.presentation.model;

import lombok.Getter;

@Getter
public class UpdateAgentResponse {

  private final AgentInfoDto agentInfo;

  public UpdateAgentResponse(AgentInfoDto agentInfo) {
    this.agentInfo = agentInfo;
  }
}
