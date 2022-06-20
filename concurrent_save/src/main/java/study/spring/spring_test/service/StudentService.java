package study.spring.spring_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring.spring_test.domain.common.ComponentTest;
import study.spring.spring_test.domain.model.student.Student;
import study.spring.spring_test.domain.model.student.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;
  private final ComponentTest componentTest;

  public Student findOne(Long id) {
    return studentRepository.findById(id).orElseGet(() -> null);
  }
}
