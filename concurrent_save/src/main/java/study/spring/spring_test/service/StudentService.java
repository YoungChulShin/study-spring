package study.spring.spring_test.service;

import java.util.Optional;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.spring_test.domain.model.student.Student;
import study.spring.spring_test.domain.model.student.StudentRepository;
import study.spring.spring_test.service.exception.StudentExistException;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  @Transactional(readOnly = true)
  public Student findOne(Long id) {
    return studentRepository.findById(id).orElseGet(() -> null);
  }

  @Transactional
  public Student add(String name, int age) {
    Optional<Student> findStudent = studentRepository.findByNameAndAge(name, age);
    if (findStudent.isPresent()) {
      throw new StudentExistException("이미 생성된 확생 정보가 있습니다. name: " + name + ", age: " + age);
    }

    Student student = new Student(name, age);
    return studentRepository.save(student);
  }
}
