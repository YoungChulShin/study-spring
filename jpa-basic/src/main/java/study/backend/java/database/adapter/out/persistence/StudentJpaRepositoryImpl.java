package study.backend.java.database.adapter.out.persistence;

import static study.backend.java.database.domain.QSchool.school;
import static study.backend.java.database.domain.QStudent.student;

import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import study.backend.java.database.application.port.in.model.StudentInfo;

class StudentJpaRepositoryImpl
    extends QuerydslRepositorySupport
    implements StudentJpaRepositoryCustom {

  public StudentJpaRepositoryImpl() {
    super(StudentJpaRepositoryImpl.class);
  }

  @Override
  public StudentInfo findStudent(Long id) {
    return from(student)
        .join(school)
        .on(student.school.eq(school))
        .where(student.id.eq(id))
        .select(
            Projections.constructor(
                StudentInfo.class,
                student.id,
                student.name,
                student.age,
                school.name))
        .fetchOne();
  }
}
