package study.spring.caller.adapter.out.systeminfo.resttemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import study.spring.caller.application.port.in.model.SystemInfo;
import study.spring.caller.application.port.out.SystemInfoPort;

@Component
class RestTemplateSystemInfo implements SystemInfoPort {

  private final Logger logger = LoggerFactory.getLogger(RestTemplateSystemInfo.class);
  private final RestTemplate restTemplate;

  public RestTemplateSystemInfo(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
    this.restTemplate.setErrorHandler(new RestTemplateErrorHandler());
  }

  @Override
  public SystemInfo getSystemInfo() {
    String url = "http://localhost:8080/api/v1/system";

    logger.info("RestTemplate: 서비스 호출");
    ResponseEntity<SystemInfo> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        SystemInfo.class);

    return response.getBody();
  }
}
