package study.spring.food_delivery.presentation;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.spring.food_delivery.application.AgentFacade;
import study.spring.food_delivery.domain.AgentLocation;
import study.spring.food_delivery.domain.model.AgentInfo;
import study.spring.food_delivery.domain.model.RegisterAgentCommand;
import study.spring.food_delivery.domain.model.UpdateAgentCommand;
import study.spring.food_delivery.domain.model.UpdateAgentLocationCommand;
import study.spring.food_delivery.presentation.model.AgentInfoDto;
import study.spring.food_delivery.presentation.model.DeliveryResponse;
import study.spring.food_delivery.presentation.model.GetAgentResponse;
import study.spring.food_delivery.presentation.model.RegisterAgentRequest;
import study.spring.food_delivery.presentation.model.RegisterAgentResponse;
import study.spring.food_delivery.presentation.model.UpdateAgentLocationResponse;
import study.spring.food_delivery.presentation.model.UpdateAgentRequest;
import study.spring.food_delivery.presentation.model.UpdateAgentResponse;

@RestController
@RequiredArgsConstructor
public class AgentController {

  private final AgentFacade agentFacade;

  @PostMapping("/api/v1/agents")
  public RegisterAgentResponse registerAgent(@RequestBody @Valid RegisterAgentRequest request) {
    RegisterAgentCommand command = new RegisterAgentCommand(request.getName(), request.getAge());
    Long agentId = agentFacade.registerAgent(command);

    return new RegisterAgentResponse(agentId);
  }

  @GetMapping("/api/v1/agents/{id}")
  public GetAgentResponse getAgent(@PathVariable Long id) {
    AgentInfo agentInfo = agentFacade.getAgentInfo(id);

    return new GetAgentResponse(AgentInfoDto.from(agentInfo));
  }

  @PutMapping("/api/v1/agents/{id}")
  public UpdateAgentResponse updateAgent(
      @PathVariable Long id,
      @RequestBody @Valid UpdateAgentRequest request) {
    UpdateAgentCommand command = new UpdateAgentCommand(
        id,
        request.getName(),
        request.getAge());
    AgentInfo agentInfo = agentFacade.updateAgent(command);

    return new UpdateAgentResponse(AgentInfoDto.from(agentInfo));
  }

  @PostMapping("/api/v1/agents/{id}/delivery")
  public DeliveryResponse delivery(@PathVariable Long id) {
    long deliverySum = agentFacade.delivery(id);

    return new DeliveryResponse(deliverySum);
  }

  @PutMapping("/api/v1/agents/{id}/location")
  public UpdateAgentLocationResponse updateAgentLocation(
      @PathVariable Long id,
      @RequestParam(name = "longitude") Double longitude,
      @RequestParam(name = "latitude") Double latitude) {
    UpdateAgentLocationCommand command = new UpdateAgentLocationCommand(
        id,
        new AgentLocation(longitude, latitude));
    Long agentId = agentFacade.updateAgentLocation(command);

    return new UpdateAgentLocationResponse(agentId);
  }
}
