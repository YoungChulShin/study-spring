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
    return response.getStatusCode().is5xxServerError();
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    logger.error("에러가 발생했습니다.");
  }
}
