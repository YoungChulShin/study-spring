package study.spring.caller.application.v1;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class SystemInfoRequestErrorHandler implements ResponseErrorHandler {

  private final Logger logger = LoggerFactory.getLogger(SystemInfoRequestErrorHandler.class);

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return !response.getStatusCode().is2xxSuccessful();
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    if (response.getStatusCode().is5xxServerError()) {
      throw new RuntimeException("호출 중에 에러가 발생했습니다. 호출 서버에서 에러가 발생했습니다.");
    } else if (response.getStatusCode().is4xxClientError()) {
      throw new RuntimeException("호출 중에 에러가 발생했습니다. 호출 정보가 잘못되었습니다.");
    } else {
      throw new RuntimeException("호출 중에 에러가 발생했습니다.");
    }
  }
}
