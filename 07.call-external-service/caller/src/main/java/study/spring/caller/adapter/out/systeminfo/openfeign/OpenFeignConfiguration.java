package study.spring.caller.adapter.out.systeminfo.openfeign;


import feign.Logger;
import feign.Logger.Level;
import feign.RequestInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Feign client
 *
 * default options<br>
 * Decoder – ResponseEntityDecoder, which wraps SpringDecoder, used to decode the Response<br>
 * Encoder – SpringEncoder is used to encode the RequestBody.<br>
 * Logger – Slf4jLogger is the default logger used by Feign.<br>
 * Contract – SpringMvcContract, which provides annotation processing<br>
 * Feign-Builder – HystrixFeign.Builder is used to construct the components.<br>
 * Client – LoadBalancerFeignClient or default Feign client<br>
 */

@Configuration
@ConditionalOnProperty(value = "system-info.external-call.type", havingValue = "openfeign")
class OpenFeignConfiguration {

  @Bean
  OkHttpClient client() {
    return new OkHttpClient();
  }

  /**
   * Logger Level
   *
   * NONE – no logging, which is the default<br>
   * BASIC – log only the request method, URL and response status<br>
   * HEADERS – log the basic information together with request and response headers<br>
   * FULL – log the body, headers and metadata for both request and response<br>
   */
  @Bean
  Logger.Level feignLoggerLevel() {
    return Level.BASIC;
  }

}
