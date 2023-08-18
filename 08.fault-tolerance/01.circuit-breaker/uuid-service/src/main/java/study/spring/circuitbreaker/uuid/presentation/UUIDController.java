package study.spring.circuitbreaker.uuid.presentation;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UUIDController {

  private final Logger logger = LoggerFactory.getLogger(UUIDController.class);

  @PostMapping("/api/v1/uuids")
  public UUIDData generateUUID() {
    UUIDData uuid = new UUIDData(UUID.randomUUID().toString());
    logger.info(uuid.toString());

    return uuid;
  }

  private static record UUIDData(String uuid) { }
}
