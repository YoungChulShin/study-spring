package study.spring.food_delivery.presentation;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring.food_delivery.presentation.model.RegisterAgentRequest;
import study.spring.food_delivery.presentation.model.RegisterAgentResponse;

@RestController
public class AgentController {

  @PostMapping("/api/v1/agents")
  RegisterAgentResponse registerAgent(@RequestBody @Valid RegisterAgentRequest request) {
    return new RegisterAgentResponse(1L);
  }
}
