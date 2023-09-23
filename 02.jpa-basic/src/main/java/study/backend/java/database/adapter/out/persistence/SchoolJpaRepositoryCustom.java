package study.backend.java.database.adapter.out.persistence;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.backend.java.database.application.port.in.model.SchoolInfo;
import study.backend.java.database.application.port.in.model.StudentInfo;

public interface SchoolJpaRepositoryCustom {

  List<SchoolInfo> findAllSchoolInfos();

  Page<StudentInfo> findStudents(Long schoolId, Pageable pageable);

  List<StudentInfo> findStudents(Long schoolId);

}
