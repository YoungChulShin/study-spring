package study.spring.spring_test.domain.model.student;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
@Table(
    name = "students",
    uniqueConstraints = {@UniqueConstraint(name = "UniqueNameAndAge", columnNames = {"name", "age"})})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private int age;

  public Student(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
