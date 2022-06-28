package study.spring.test_with_bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolConfiguration {

  @Bean
  public School school() {
    return new School();
  }
}
