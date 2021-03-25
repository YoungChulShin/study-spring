package study.spring.spring_test.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.spring.spring_test.domain.common.ComponentTest;
import study.spring.spring_test.domain.model.student.Student;
import study.spring.spring_test.service.StudentService;

@RestController
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;
  private final ComponentTest componentTest;

  @GetMapping("/{id}")
  public Student findStudent(@PathVariable(name = "id") Long id) {
    String testValue = componentTest.getTestValue();
    return studentService.findOne(id);
  }
}
