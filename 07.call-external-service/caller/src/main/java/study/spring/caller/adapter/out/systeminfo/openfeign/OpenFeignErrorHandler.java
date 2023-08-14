package study.spring.caller.adapter.out.systeminfo.openfeign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
@ConditionalOnProperty(value = "system-info.external-call.type", havingValue = "openfeign")
class OpenFeignErrorHandler implements ErrorDecoder {

  private final Logger logger = LoggerFactory.getLogger(OpenFeignErrorHandler.class);

  @Override
  public Exception decode(String methodKey, Response response) {
    logger.info("RestTemplate: 에러 발생");

    HttpStatus httpStatus = HttpStatus.valueOf(response.status());
    if (httpStatus.is5xxServerError()) {
      throw new RuntimeException("호출 중에 에러가 발생했습니다. 호출 서버에서 에러가 발생했습니다.");
    } else if (httpStatus.is4xxClientError()) {
      throw new RuntimeException("호출 중에 에러가 발생했습니다. 호출 정보가 잘못되었습니다.");
    } else {
      throw new RuntimeException("호출 중에 에러가 발생했습니다.");
    }
  }
}
