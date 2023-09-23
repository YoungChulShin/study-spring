package study.backend.java.database.application.port.in;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.backend.java.database.application.port.in.model.SchoolInfo;
import study.backend.java.database.application.port.in.model.StudentInfo;
import study.backend.java.database.domain.School;

public interface SchoolUseCase {

  Long addSchool(String name);

  School getSchool(Long id);

  List<StudentInfo> findStudents(Long schoolId);

  Page<StudentInfo> findStudents(Long schoolId, Pageable pageable);

  List<SchoolInfo> findSchools();
}
