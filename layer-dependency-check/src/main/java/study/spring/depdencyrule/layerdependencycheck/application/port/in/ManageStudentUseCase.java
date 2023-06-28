package study.spring.depdencyrule.layerdependencycheck.application.port.in;

import study.spring.depdencyrule.layerdependencycheck.application.port.in.model.AddStudentCommand;

public interface ManageStudentUseCase {

  String add(AddStudentCommand command);
}
