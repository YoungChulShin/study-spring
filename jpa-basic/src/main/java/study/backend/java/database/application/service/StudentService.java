package study.backend.java.database.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.backend.java.database.application.port.in.StudentUseCase;
import study.backend.java.database.application.port.out.SchoolPort;
import study.backend.java.database.application.port.out.StudentPort;
import study.backend.java.database.domain.School;
import study.backend.java.database.domain.Student;

@Service
class StudentService implements StudentUseCase {

  private final StudentPort studentPort;
  private final SchoolPort schoolPort;

  public StudentService(StudentPort studentPort, SchoolPort schoolPort) {
    this.studentPort = studentPort;
    this.schoolPort = schoolPort;
  }

  @Override
  @Transactional
  public Long addStudent(String name, Integer age, Long schoolId) {
    School school = schoolPort.findById(schoolId);
    Student initStudent = new Student(name, age, school);
    Student student = studentPort.save(initStudent);

    return student.getId();
  }

  // TODO [ycshin]: fetchjoin + info class로 변경 필요
  @Override
  @Transactional(readOnly = true)
  public Student getStudent(Long id) {
    // 방법 1: 명시적 조회
    // Student student = studentPort.findById(id);
    // School school = student.getSchool();
    // String schoolName = school.getName();

    // 방법 2: fetch join
    // select s1_0.id,s1_0.age,s1_0.name,s2_0.id,s2_0.name
    // from students s1_0
    // join schools s2_0 on s2_0.id=s1_0.school_id
    // where s1_0.id=2;

    return studentPort.findWithSchoolById(id);
  }
}
