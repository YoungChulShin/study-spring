package study.spring.caller.adapter.out.systeminfo.openfeign;


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

@ConditionalOnProperty(value = "system-info.external-call.type", havingValue = "openfeign")
@Configuration
class OpenFeignConfiguration {

  @Bean
  OkHttpClient client() {
    return new OkHttpClient();
  }

}
