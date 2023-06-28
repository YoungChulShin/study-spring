package study.spring.depdencyrule.layerdependencycheck.domain;

import java.util.UUID;

public class Student {

  private String id;
  private String name;
  private int age;

  public Student(String name, int age) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.age = age;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
