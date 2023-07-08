package study.backend.java.database.application.port.out;

import java.util.List;
import study.backend.java.database.application.port.in.model.SchoolInfo;
import study.backend.java.database.domain.School;

public interface SchoolPort {

  School save(School school);

  School findById(Long id);

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
