package study.spring.caller.infra;

import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class HelloApiClientImpl implements HelloApiClient {

  private final RestTemplate restTemplate;
  private final String serviceHost = "http://localhost:5001";
  private final String serviceUrl = "/greeting/hello";

  public HelloApiClientImpl() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setReadTimeout(3000);
    factory.setConnectTimeout(5000);
    factory.setConnectionRequestTimeout(5000);

    this.restTemplate = new RestTemplate(factory);
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
