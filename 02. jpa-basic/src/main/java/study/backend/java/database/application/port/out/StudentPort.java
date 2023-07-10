package study.backend.java.database.application.port.out;

import study.backend.java.database.application.port.in.model.StudentInfo;
import study.backend.java.database.domain.Student;

public interface StudentPort {

  Student save(Student student);

  Student findById(Long id);

  Student findWithSchoolById(Long id);

  StudentInfo findStudent(Long id);
}
