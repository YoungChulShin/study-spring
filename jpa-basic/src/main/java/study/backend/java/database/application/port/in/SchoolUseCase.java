package study.backend.java.database.application.port.in;

import java.util.List;
import study.backend.java.database.application.port.in.model.StudentInfo;
import study.backend.java.database.domain.School;

public interface SchoolUseCase {

  Long addSchool(String name);

  School getSchool(Long id);

  List<StudentInfo> findStudents(Long schoolId);
}
