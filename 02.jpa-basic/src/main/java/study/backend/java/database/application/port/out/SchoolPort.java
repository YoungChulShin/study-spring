package study.backend.java.database.application.port.out;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.backend.java.database.application.port.in.model.SchoolInfo;
import study.backend.java.database.application.port.in.model.StudentInfo;
import study.backend.java.database.domain.School;
import study.backend.java.database.domain.Student;

public interface SchoolPort {

  School save(School school);

  School findById(Long id);

  List<StudentInfo> findStudents(Long schoolId);

  Page<StudentInfo> findStudents(Long schoolId, Pageable pageable);

  /**
   * School Entity를 직접 조회
   */
  List<School> findAll();

  /**
   * School Entity를 조회하는데, Student와 FetchJoin 처리
   */
  List<School> findAllWithStudents();

  /**
   * Querydsl을 이용해서 School과 Student를 Join하는 SchoolInfo를 조회
   */
  List<SchoolInfo> findAllSchoolInfos();
}
