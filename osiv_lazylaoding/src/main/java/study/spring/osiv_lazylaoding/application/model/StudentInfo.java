package study.spring.osiv_lazylaoding.application.model;

import lombok.Getter;

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
}
