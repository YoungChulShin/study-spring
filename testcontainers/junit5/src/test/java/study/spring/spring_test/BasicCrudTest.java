package study.spring.spring_test;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import study.spring.spring_test.domain.model.student.Student;
import study.spring.spring_test.domain.model.student.StudentRepository;

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
public class BasicCrudTest {

  @Autowired
  private StudentRepository studentRepository;

  @Container
  private JdbcDatabaseContainer testContainer = new MySQLContainer(DockerImageName.parse("mysql:8.0"))
      .withDatabaseName("spring_test")
      .withUsername("root")
      .withPassword("1323")
      .withInitScript("db/init.sql");

  @Test
  public void initTest() {
    // given, when
    String host = testContainer.getHost();
    String databaseName = testContainer.getDatabaseName();
    Integer firstMappedPort = testContainer.getFirstMappedPort();

    // then
    Assertions.assertThat(host).isEqualTo("localhost");
    Assertions.assertThat(databaseName).isEqualTo("spring_test");
    Assertions.assertThat(firstMappedPort).isNotNull();
  }

  @Test
  public void saveAndGet() {
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
