package study.spring.spring_test;

import java.util.Optional;
import javax.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import study.spring.spring_test.base.BaseTestContainer;
import study.spring.spring_test.domain.model.student.Student;
import study.spring.spring_test.domain.model.student.StudentRepository;

@SpringBootTest
@Rollback
public class _05_AbstractCrudTest extends BaseTestContainer {

  @Autowired
  private StudentRepository studentRepository;

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
