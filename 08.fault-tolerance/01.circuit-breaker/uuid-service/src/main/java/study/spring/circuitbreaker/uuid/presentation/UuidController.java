package study.spring.circuitbreaker.uuid.presentation;

import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UuidController {

  @PostMapping("/api/v1/uuids")
  public String generateUuid() {
    return UUID.randomUUID().toString();
  }
}
