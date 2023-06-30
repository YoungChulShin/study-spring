package study.spring.depdencyrule.layerdependencycheck.student.application.port.in;

import study.spring.depdencyrule.layerdependencycheck.student.application.port.in.model.AddStudentCommand;

public interface ManageStudentUseCase {

  String add(AddStudentCommand command);
}
