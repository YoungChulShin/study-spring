package study.backend.java.database.adapter.out.persistence;

import static study.backend.java.database.domain.QSchool.school;
import static study.backend.java.database.domain.QStudent.student;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

  @Override
  public Page<StudentInfo> findStudents(Long schoolId, Pageable pageable) {
    JPQLQuery<StudentInfo> query = from(school)
        .join(student).on(school.id.eq(student.school.id))
        .where(school.id.eq(schoolId))
        .select(Projections.constructor(
            StudentInfo.class,
            student.id,
            student.name,
            student.age,
            school.name));

    JPQLQuery<StudentInfo> pageableQuery = getQuerydsl().applyPagination(pageable, query);
    QueryResults<StudentInfo> result = pageableQuery.fetchResults();
    return new PageImpl<>(result.getResults(), pageable, result.getTotal());
  }

  @Override
  public List<StudentInfo> findStudents(Long schoolId) {
    return from(school)
        .join(student).on(school.id.eq(student.school.id))
        .where(school.id.eq(schoolId))
        .select(Projections.constructor(
            StudentInfo.class,
            student.id,
            student.name,
            student.age,
            school.name))
        .fetch();
  }
}
