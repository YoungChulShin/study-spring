package study.spring.caller.application.v1;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import study.spring.caller.application.CommonInfo.CalleeService;
import study.spring.caller.application.model.SystemInfo;

@Service
public class SystemInfoServiceV1 {

  private final RestTemplate restTemplate;

  public SystemInfoServiceV1(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    this.restTemplate.setErrorHandler(new SystemInfoRequestErrorHandler());
  }

  public SystemInfo getSystemInfo() {
    String url = CalleeService.HOST + "/" + CalleeService.URL_SYSTEM_INFO;

    ResponseEntity<SystemInfo> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        SystemInfo.class);

    if (response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    }

    if (response.getStatusCode().is5xxServerError()) {
      throw new RuntimeException("호출 중에 에러가 발생했습니다. 호출 서버에서 에러가 발생했습니다.");
    } else if (response.getStatusCode().is4xxClientError()) {
      throw new RuntimeException("호출 중에 에러가 발생했습니다. 호출 정보가 잘못되었습니다.");
    } else {
      throw new RuntimeException("호출 중에 에러가 발생했습니다.");
    }
  }
}
