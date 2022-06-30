package study.spring.object_mapper_test;

public class School {

  private String name;
  private String location;
  private int age;

  public School(String name, String location, int age) {
    this.name = name;
    this.location = location;
    this.age = age;
  }

  private School() {}

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public int getAge() {
    return age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    School school = (School) o;

    if (age != school.getAge()) {
      return false;
    }

    if (name != null ? !name.equals(school.getName()) : school.getName() != null) {
      return false;
    }

    return location != null ? location.equals(school.getLocation()) : school.getLocation() == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + ((location != null) ? location.hashCode() : 0);
    result = 31 * result + age;
    return result;
  }
}
