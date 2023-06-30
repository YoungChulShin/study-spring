package study.spring.depdencyrule.layerdependencycheck.student.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring.depdencyrule.layerdependencycheck.student.application.port.in.ManageStudentUseCase;
import study.spring.depdencyrule.layerdependencycheck.student.application.port.in.model.AddStudentCommand;

@RestController
class StudentController {

  private final ManageStudentUseCase manageStudentUseCase;

  StudentController(ManageStudentUseCase manageStudentUseCase) {
    this.manageStudentUseCase = manageStudentUseCase;
  }

  @PostMapping("/students")
  String addStudent(@RequestBody AddStudentRequest request) {
    return manageStudentUseCase.add(new AddStudentCommand(request.getName(), request.getAge()));
  }

  private record AddStudentRequest(String name, Integer age) {

    public String getName() {
      return name;
    }

    public Integer getAge() {
      return age;
    }
  }
}
