package study.spring.osiv_lazylaoding.application.model;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class CreateStudentCommand {

  private final String name;
  private final long age;
  private final String schoolName;

  public CreateStudentCommand(String name, long age, String schoolName) {
    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("name");
    }
    if (age <= 0) {
      throw new IllegalArgumentException("age");
    }
    if (StringUtils.isEmpty(schoolName)) {
      throw new IllegalArgumentException("schoolName");
    }

    this.name = name;
    this.age = age;
    this.schoolName = schoolName;
  }
}
