package study.backend.java.database.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.backend.java.database.application.port.in.StudentUseCase;
import study.backend.java.database.application.port.in.model.StudentInfo;
import study.backend.java.database.application.port.out.SchoolReader;
import study.backend.java.database.application.port.out.StudentRerader;
import study.backend.java.database.application.port.out.StudentWriter;
import study.backend.java.database.domain.School;
import study.backend.java.database.domain.Student;

@Service
class StudentService implements StudentUseCase {

  private final StudentWriter studentWriter;
  private final StudentRerader studentRerader;
  private final SchoolReader schoolReader;

  public StudentService(
      StudentWriter studentWriter,
      StudentRerader studentRerader,
      SchoolReader schoolReader) {
    this.studentWriter = studentWriter;
    this.studentRerader = studentRerader;
    this.schoolReader = schoolReader;
  }

  @Override
  @Transactional
  public Long addStudent(String name, Integer age, Long schoolId) {
    School school = schoolReader.findById(schoolId);
    Student initStudent = new Student(name, age, school);
    Student student = studentWriter.save(initStudent);

    return student.getId();
  }

  @Override
  @Transactional(readOnly = true)
  public StudentInfo getStudent(Long id) {
    // 방법 1: 명시적 조회
    // Student student = studentPort.findById(id);
    // School school = student.getSchool();
    // String schoolName = school.getName();

    // 방법 2: fetch join
    // select s1_0.id,s1_0.age,s1_0.name,s2_0.id,s2_0.name
    // from students s1_0
    // join schools s2_0 on s2_0.id=s1_0.school_id
    // where s1_0.id=2;

    // 방법 3: Querydsl과 CustomClass를 이용한 조회
    // select s1_0.id, s1_0.name, s1_0.age, s2_0.name
    // from students s1_0
    // join schools s2_0 on s1_0.school_id = s2_0.id
    // where s1_0.id = 1;

    return studentRerader.findStudent(id);
  }
}
