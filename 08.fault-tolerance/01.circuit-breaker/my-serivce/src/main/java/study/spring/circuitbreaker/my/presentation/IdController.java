package study.spring.circuitbreaker.my.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.circuitbreaker.my.application.IdService;

@RestController
class IdController {

  private final IdService idService;

  IdController(IdService idService) {
    this.idService = idService;
  }

  @PostMapping("api/v1/ids")
  public String generateId() {
    return idService.generateId();
  }
}
