package study.spring.osiv_lazylaoding.presentation;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring.osiv_lazylaoding.application.StudentService;
import study.spring.osiv_lazylaoding.application.model.CreateStudentCommand;
import study.spring.osiv_lazylaoding.application.model.StudentInfo;
import study.spring.osiv_lazylaoding.presentation.model.CreateStudentRequest;
import study.spring.osiv_lazylaoding.presentation.model.GetStudentResponse;
import study.spring.osiv_lazylaoding.presentation.model.UpdateStudentNameRequest;

@RestController
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @PostMapping("/api/student")
  public Long createStudent(@RequestBody @Valid CreateStudentRequest request) {
    return studentService.createStudent(new CreateStudentCommand(
        request.getName(),
        request.getAge(),
        request.getSchoolName()));
  }

  @GetMapping("/api/students/{id}")
  public GetStudentResponse getStudent(@PathVariable(name = "id") Long id) {
    StudentInfo studentInfo = studentService.findStudent(id);
    return new GetStudentResponse(studentInfo);
  }

  @PostMapping("/api/students/{id}/update-name")
  public void getStudentWithEvent(
      @PathVariable(name = "id") Long id,
      @RequestBody @Valid UpdateStudentNameRequest request) {
    studentService.updateStudentName(id, request.getName());
  }
}
