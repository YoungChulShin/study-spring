package study.spring.food_delivery.presentation.model;

import lombok.Getter;

@Getter
public class GetAgentResponse {

  private final AgentInfoDto agentInfo;

  public GetAgentResponse(AgentInfoDto agentInfo) {
    this.agentInfo = agentInfo;
  }
}
