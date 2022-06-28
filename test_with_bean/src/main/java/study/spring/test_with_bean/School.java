package study.spring.test_with_bean;

import org.springframework.beans.factory.annotation.Value;

public class School {

  @Value("${application.school.name}")
  private String name;
  @Value("${application.school.location}")
  private String location;
  @Value("${application.school.age}")
  private int age;

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public int getAge() {
    return age;
  }
}
