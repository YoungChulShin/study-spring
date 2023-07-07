package study.backend.java.database.application.port.in;

import study.backend.java.database.application.port.in.model.StudentInfo;
import study.backend.java.database.domain.Student;

public interface StudentUseCase {

  Long addStudent(String name, Integer age, Long schoolId);

  StudentInfo getStudent(Long id);
}
