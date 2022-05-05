package study.spring.spring_test.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ComponentTest {

  private String testValue;

  public ComponentTest(@Value("${myValue.value1}") String testValue) {
    this.testValue = testValue;
  }
}
