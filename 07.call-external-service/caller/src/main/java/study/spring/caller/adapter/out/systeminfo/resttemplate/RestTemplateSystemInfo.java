package study.spring.caller.adapter.out.systeminfo.resttemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import study.spring.caller.application.port.in.model.SystemInfo;
import study.spring.caller.application.port.out.SystemInfoPort;

@Component
class RestTemplateSystemInfo implements SystemInfoPort {

  private final RestTemplate restTemplate;

  public RestTemplateSystemInfo(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
    this.restTemplate.setErrorHandler(new RestTemplateErrorHandler());
  }

  @Override
  public SystemInfo getSystemInfo() {
    String url = "http://localhost:8080/api/v1/system";

    ResponseEntity<SystemInfo> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        SystemInfo.class);

    return response.getBody();
  }
}
