package study.spring.depdencyrule.layerdependencycheck.application;

import org.springframework.stereotype.Service;
import study.spring.depdencyrule.layerdependencycheck.application.port.in.ManageStudentUseCase;
import study.spring.depdencyrule.layerdependencycheck.application.port.in.model.AddStudentCommand;
import study.spring.depdencyrule.layerdependencycheck.domain.Student;

@Service
class ManageStudentService implements ManageStudentUseCase {

  @Override
  public String add(AddStudentCommand command) {
    Student student = new Student(command.getName(), command.getAge());
    return student.getId();
  }
}
