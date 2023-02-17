package study.spring.osiv_lazylaoding.application.model;

import lombok.Getter;
import study.spring.osiv_lazylaoding.domain.Student;

@Getter
public class StudentInfo {

  private final String name;
  private final long age;
  private final String schoolName;

  public StudentInfo(String name, long age, String schoolName) {
    this.name = name;
    this.age = age;
    this.schoolName = schoolName;
  }

  public StudentInfo(Student student) {
    this.name = student.getName();
    this.age = student.getAge();
    this.schoolName = student.getSchoolName();
  }
}
