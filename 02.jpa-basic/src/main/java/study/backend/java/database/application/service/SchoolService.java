package study.backend.java.database.application.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.backend.java.database.application.port.in.SchoolUseCase;
import study.backend.java.database.application.port.in.model.SchoolInfo;
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
  public List<StudentInfo> findStudents(Long schoolId) {
    return null;
  }

  @Override
  @Transactional(readOnly = true)
  public Page<StudentInfo> findStudents(Long schoolId, Pageable pageable) {
    return schoolPort.findStudents(schoolId, pageable);
  }

  @Override
  @Transactional(readOnly = true)
  public List<SchoolInfo> findSchools() {
    // 방법 1. Entity 조회 + LazyLoading => N+1 문제 발생
    // 1) select s1_0.id,s1_0.name from schools s1_0;
    // 2) select s1_0.school_id,s1_0.id,s1_0.age,s1_0.name from students s1_0 where s1_0.school_id=1;
    // 3) select s1_0.school_id,s1_0.id,s1_0.age,s1_0.name from students s1_0 where s1_0.school_id=2;
    // 4) select s1_0.school_id,s1_0.id,s1_0.age,s1_0.name from students s1_0 where s1_0.school_id=3
    // 5) select s1_0.school_id,s1_0.id,s1_0.age,s1_0.name from students s1_0 where s1_0.school_id=4;
    List<SchoolInfo> schoolInfos1 = findSchoolsByEntity();

    // 방법 2. Entity 조회 + fetch Join
    // 1) select s1_0.id,s1_0.name,s2_0.school_id,s2_0.id,s2_0.age,s2_0.name from schools s1_0 join students s2_0 on s1_0.id=s2_0.school_id;
    List<SchoolInfo> schoolInfos2 = findSchoolsFetchJoin();

    // 방법 3. Querydsl에서 명시적으로 조회
    // 1) select s1_0.id,s1_0.name,s2_0.id,s2_0.name,s2_0.age from schools s1_0 join students s2_0 on s1_0.id=s2_0.school_id;
    List<SchoolInfo> schoolInfos3 = findSchoolsQuerydsl();

    return schoolInfos3;
  }

  private List<SchoolInfo> findSchoolsByEntity() {
    List<School> schools = schoolPort.findAll();
    return schools.stream()
        .map(school -> new SchoolInfo(
            school.getId(),
            school.getName(),
            school.getStudents().stream()
                .map(student -> new StudentInfo(
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    school.getName()))
                .collect(Collectors.toList())))
        .collect(Collectors.toList());
  }

  private List<SchoolInfo> findSchoolsFetchJoin() {
    List<School> schools = schoolPort.findAllWithStudents();
    return schools.stream()
        .map(school -> new SchoolInfo(
            school.getId(),
            school.getName(),
            school.getStudents().stream()
                .map(student -> new StudentInfo(
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    school.getName()))
                .collect(Collectors.toList())))
        .collect(Collectors.toList());
  }

  private List<SchoolInfo> findSchoolsQuerydsl() {
    return schoolPort.findAllSchoolInfos();
  }
}
