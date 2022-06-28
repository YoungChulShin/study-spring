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
// 1. properties 속성을 이용해서 의존을 바로 주입해줄 수 있다
//@TestPropertySource(properties = {
//    "application.school.name=ycschool",
//    "application.school.location=seoul gandong",
//    "application.school.age=30"
//})
// 2. location을 지정할 수 있는데, 이 경우 *.properties 또는 *.xml 파일이어야한다
@TestPropertySource(locations = "classpath:application-test.properties")
// 3. properties와 location을 모두 지정하면, properties가 높은 우선순위를 가진다
public class SchoolTest {

  @Autowired
  private School school;

  @Test
  void test() {
    Assertions.assertThat(school.getName()).isEqualTo("mjschool");
  }
}
