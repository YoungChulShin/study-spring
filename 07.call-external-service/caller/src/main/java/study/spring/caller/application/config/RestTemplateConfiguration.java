package study.spring.caller.application.config;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

  @Bean
  public RestTemplateBuilder systemInfoRequestBuilder() {
    ObjectMapper objectMapper = new ObjectMapper();
    // 요청 데이터에는 있지만, 역질렬화 대상이 되는 클래스에는 없을 경우 에러 발생 여부 설정
    objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.disable(FAIL_ON_NULL_FOR_PRIMITIVES);
    objectMapper.enable(READ_UNKNOWN_ENUM_VALUES_AS_NULL);

    var converter = new MappingJackson2HttpMessageConverter();
    converter.setObjectMapper(objectMapper);

    return new RestTemplateBuilder()
        .additionalMessageConverters(converter)
        .setConnectTimeout(Duration.ofSeconds(3))
        .setReadTimeout(Duration.ofSeconds(5));
  }
}
