package study.spring.circuitbreaker.my.infra;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import study.spring.circuitbreaker.my.domain.UUIDData;
import study.spring.circuitbreaker.my.domain.UUIDGenerator;

@Component
class ExternalUUIDGenerator implements UUIDGenerator {

  private final RestTemplate restTemplate;

  ExternalUUIDGenerator(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @CircuitBreaker(
      name = "uuidService",
      fallbackMethod = "fallback")
  @Override
  public UUIDData generateUUID() {
    var result = restTemplate.exchange(
        "http://localhost:8080/api/v1/uuids",
        HttpMethod.POST,
        null,
        UUIDData.class);

    return result.getBody();
  }

  private UUIDData fallback(Exception e) {
    return new UUIDData("my-" + UUID.randomUUID());
  }
}
