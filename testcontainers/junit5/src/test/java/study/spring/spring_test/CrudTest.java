package study.spring.spring_test;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.spring.spring_test.domain.model.student.Student;
import study.spring.spring_test.domain.model.student.StudentRepository;

@SpringBootTest
public class CrudTest {

  @Autowired
  private StudentRepository studentRepository;

  @Test
  public void saveAndGet() {
    // save
    String name = "ycshin";
    int age = 20;
    Student student = new Student(name, age);
    studentRepository.save(student);

    // load
    Optional<Student> findStudent = studentRepository.findById(student.getId());

    // check
    Assertions.assertThat(findStudent).isNotEmpty();
    Assertions.assertThat(findStudent.get().getName()).isEqualTo(name);
    Assertions.assertThat(findStudent.get().getAge()).isEqualTo(age);
  }
}
