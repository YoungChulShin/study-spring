package study.spring.test_with_bean;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {SchoolConfiguration.class})
// 1. properties 속성을 이용해서 의존을 바로 주입해줄 수 있다
//@TestPropertySource(properties = {
//    "application.school.name=ycschool",
//    "application.school.location=seoul gandong",
//    "application.school.age=30"
//})
// 2. location을 지정할 수 있는데, 이 경우 *.properties 또는 *.xml 파일이어야한다
//@TestPropertySource(locations = "classpath:application-test.properties")
// 3. properties와 location을 모두 지정하면, properties가 높은 우선순위를 가진다
// 4. yml 파일을 사용하고 싶다면, 부트 2.4 이후부터는 ConfigDataApplicationContextInitializer를 지원해서 사용할 수 있다
// 이때 activeProfiles를 이용해서 설정 파일을 지정해준다
@ContextConfiguration(
    initializers = {ConfigDataApplicationContextInitializer.class},
    classes = {SchoolConfiguration.class}
)
@ActiveProfiles("test")
public class SchoolTest {

  @Autowired
  private School school;

  @Test
  void test() {
    Assertions.assertThat(school.getName()).isEqualTo("mjschool");
  }
}
