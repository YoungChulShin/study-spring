package study.spring.depdencyrule.layerdependencycheck.application.port.in.model;

public class AddStudentCommand {

  private final String name;
  private final int age;

  public AddStudentCommand(String name, Integer age) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("name");
    }

    if (age == null || age <= 0) {
      throw new IllegalArgumentException("name");
    }

    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
