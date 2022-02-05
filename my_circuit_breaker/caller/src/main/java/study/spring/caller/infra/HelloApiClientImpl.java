package study.spring.caller.infra;

import java.net.URI;
import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class HelloApiClientImpl implements HelloApiClient {

  private final RestTemplate restTemplate;
  private final String serviceHost = "http://localhost:5001";
  private final String serviceUrl = "/greeting/hello";

  public HelloApiClientImpl() {
    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    restTemplateBuilder.setReadTimeout(Duration.ofSeconds(5000));
    restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(3000));
    restTemplate = restTemplateBuilder.build();
  }

  @Override
  public String hello(String name) {
    URI uri = UriComponentsBuilder
        .fromUriString(serviceHost + serviceUrl + "/" + name)
        .build()
        .toUri();

    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
    return result.getBody();
  }
}
