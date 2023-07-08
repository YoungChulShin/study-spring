package study.backend.java.database.application.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.backend.java.database.application.port.in.SchoolUseCase;
import study.backend.java.database.application.port.in.model.StudentInfo;
import study.backend.java.database.application.port.out.SchoolPort;
import study.backend.java.database.domain.School;

@Service
class SchoolService implements SchoolUseCase {

  private final SchoolPort schoolPort;

  public SchoolService(SchoolPort schoolPort) {
    this.schoolPort = schoolPort;
  }

  @Override
  @Transactional
  public Long addSchool(String name) {
    School initSchool = new School(name);
    School school = schoolPort.save(initSchool);
    return school.getId();
  }

  @Override
  @Transactional(readOnly = true)
  public School getSchool(Long id) {
    return schoolPort.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<StudentInfo> findStudents(Long schoolId) {
    School school = schoolPort.findById(schoolId);

    return school.getStudents().stream()
        .map(student -> new StudentInfo(
            student.getId(),
            student.getName(),
            student.getAge(),
            student.getSchool().getName()))
        .collect(Collectors.toList());
  }
}
