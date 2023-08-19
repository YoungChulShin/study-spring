package study.spring.circuitbreaker.my.application;

import org.springframework.stereotype.Service;
import study.spring.circuitbreaker.my.domain.UUIDGenerator;

@Service
public class IdService {

  private final UUIDGenerator uuidGenerator;

  IdService(UUIDGenerator uuidGenerator) {
    this.uuidGenerator = uuidGenerator;
  }

  public String generateId() {
    return uuidGenerator.generateUUID();
  }
}
