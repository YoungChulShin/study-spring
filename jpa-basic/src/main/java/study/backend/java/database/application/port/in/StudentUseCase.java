package study.backend.java.database.application.port.in;

import study.backend.java.database.domain.Student;

public interface StudentUseCase {

  Long addStudent(String name, Integer age, Long schoolId);

  Student getStudent(Long id);
}
