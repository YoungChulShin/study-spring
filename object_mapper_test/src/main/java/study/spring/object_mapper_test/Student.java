package study.spring.object_mapper_test;

public class Student {

  private String name;
  private Gender gender;

  public Student(String name, Gender gender) {
    this.name = name;
    this.gender = gender;
  }

  private Student() {}

  public String getName() {
    return name;
  }

  public Gender getGender() {
    return gender;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Student student = (Student) o;

    if (name != null ? !name.equals(student.name) : student.name != null) {
      return false;
    }
    return gender == student.gender;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (gender != null ? gender.hashCode() : 0);
    return result;
  }
}
