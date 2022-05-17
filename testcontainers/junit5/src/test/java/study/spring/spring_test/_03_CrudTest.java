package study.spring.spring_test;

import java.util.Optional;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import study.spring.spring_test.domain.model.student.Student;
import study.spring.spring_test.domain.model.student.StudentRepository;

@SpringBootTest
@Testcontainers
@Rollback
public class _03_CrudTest {

  @Autowired
  private StudentRepository studentRepository;

  @Container
  private static MySQLContainer<?> testContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
      .withDatabaseName("spring_test")
      .withUsername("root")
      .withPassword("1323")
      .withInitScript("db/init.sql");

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", testContainer::getJdbcUrl);
    registry.add("spring.datasource.username", testContainer::getUsername);
    registry.add("spring.datasource.password", testContainer::getPassword);
  }

  @Test
  @Transactional
  void saveAndGet() {
    // given
    String name = "ycshin";
    int age = 99;
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
