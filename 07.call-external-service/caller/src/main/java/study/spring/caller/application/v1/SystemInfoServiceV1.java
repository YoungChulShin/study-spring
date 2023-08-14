package study.spring.caller.application.v1;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import study.spring.caller.application.CommonInfo.CalleeService;
import study.spring.caller.application.model.SystemInfo;

@Service
public class SystemInfoServiceV1 {

  private final RestTemplate restTemplate;

  public SystemInfoServiceV1(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
    this.restTemplate.setErrorHandler(new SystemInfoRequestErrorHandler());
  }

  public SystemInfo getSystemInfo() {
    String url = CalleeService.HOST + "/" + CalleeService.URL_SYSTEM_INFO;

    ResponseEntity<SystemInfo> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        SystemInfo.class);

    return response.getBody();
  }
}
