package study.spring.circuitbreaker.my.config;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
class RestTemplateConfig {

  @Bean
  RestTemplateBuilder restTemplateBuilder() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.disable(FAIL_ON_NULL_FOR_PRIMITIVES);
    objectMapper.enable(READ_UNKNOWN_ENUM_VALUES_AS_NULL);

    var jsonConverter = new MappingJackson2HttpMessageConverter();
    jsonConverter.setObjectMapper(objectMapper);

    return new RestTemplateBuilder()
        .additionalMessageConverters(
            jsonConverter,
            new StringHttpMessageConverter())
        .setConnectTimeout(Duration.ofSeconds(3))
        .setReadTimeout(Duration.ofSeconds(5));
  }
}
