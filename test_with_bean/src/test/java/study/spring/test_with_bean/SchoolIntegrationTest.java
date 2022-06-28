package study.spring.test_with_bean;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SchoolIntegrationTest {

  @Autowired
  private School school;

  @Test
  void test() {
    Assertions.assertThat(school.getName()).isEqualTo("ycschool");
  }
}
