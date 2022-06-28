package study.spring.test_with_bean;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SchoolConfiguration.class})
@TestPropertySource(properties = {
    "application.school.name=ycschool",
    "application.school.location=seoul gandong",
    "application.school.age=30"
})
public class SchoolTest {

  @Autowired
  private School school;

  @Test
  void test() {
    Assertions.assertThat(school.getName()).isEqualTo("ycschool");
  }
}
