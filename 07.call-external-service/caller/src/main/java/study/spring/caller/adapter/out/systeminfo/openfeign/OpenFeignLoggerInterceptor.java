package study.spring.caller.adapter.out.systeminfo.openfeign;

import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "system-info.external-call.type", havingValue = "openfeign")
class OpenFeignLoggerInterceptor {

  private final Logger logger = LoggerFactory.getLogger(OpenFeignLoggerInterceptor.class);

  @Bean
  RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      logger.info("OpenFeign: 서비스 호출");
      requestTemplate.header("test", "test header");
    };
  }
}
