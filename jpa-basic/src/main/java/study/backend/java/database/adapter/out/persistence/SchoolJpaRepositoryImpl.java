package study.backend.java.database.adapter.out.persistence;

import static study.backend.java.database.domain.QSchool.school;
import static study.backend.java.database.domain.QStudent.student;

import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import study.backend.java.database.application.port.in.model.SchoolInfo;
import study.backend.java.database.application.port.in.model.StudentInfo;

public class SchoolJpaRepositoryImpl
    extends QuerydslRepositorySupport
    implements SchoolJpaRepositoryCustom {

  public SchoolJpaRepositoryImpl() {
    super(SchoolJpaRepositoryImpl.class);
  }

  @Override
  public List<SchoolInfo> findAllSchoolInfos() {
    return from(school)
        .join(student).on(school.id.eq(student.school.id))
        .select(Projections.constructor(
            SchoolInfo.class,
            school.id,
            school.name,
            Projections.list(
                Projections.constructor(
                    StudentInfo.class,
                    student.id,
                    student.name,
                    student.age,
                    school.name))))
        .fetch();
  }
}
