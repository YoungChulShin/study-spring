package study.spring.spring_test.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring.spring_test.domain.model.student.Student;
import study.spring.spring_test.presentation.model.CreateStudentRequest;
import study.spring.spring_test.service.StudentService;

@RestController
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/students/{id}")
  public Student findStudent(@PathVariable(name = "id") Long id) {
    return studentService.findOne(id);
  }

  @PostMapping("/students")
  public Student addStudent(@RequestBody CreateStudentRequest request) {
    return studentService.add(request.getName(), request.getAge());
  }
}
