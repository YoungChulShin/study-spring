package study.spring.spring_test;

import java.util.Optional;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import study.spring.spring_test.base.BaseContainer;
import study.spring.spring_test.domain.model.student.Student;
import study.spring.spring_test.domain.model.student.StudentRepository;

@SpringBootTest
@Rollback
@Testcontainers
public class _04_BaseCrudTest {

  @Autowired
  private StudentRepository studentRepository;

  @ClassRule
  private static MySQLContainer mySQLContainer = BaseContainer.getInstance();

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", mySQLContainer::getUsername);
    registry.add("spring.datasource.password", mySQLContainer::getPassword);
  }

  @Test
  @Transactional
  void saveAndGet() {
    // given
    String name = "ycshin";
    int age = 20;
    Student student = new Student(name, age);
    studentRepository.save(student);

    // when
    Optional<Student> findStudent = studentRepository.findById(student.getId());

    // then
    Assertions.assertThat(findStudent).isNotEmpty();
    Assertions.assertThat(findStudent.get().getName()).isEqualTo(name);
    Assertions.assertThat(findStudent.get().getAge()).isEqualTo(age);
  }
}
