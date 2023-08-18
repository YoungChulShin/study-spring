package study.spring.circuitbreaker.my.infra;

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

  @Override
  public UUIDData generateUUID() {
    var result = restTemplate.exchange(
        "http://localhost:8080/api/v1/uuids",
        HttpMethod.POST,
        null,
        UUIDData.class);

    return result.getBody();
  }
}
