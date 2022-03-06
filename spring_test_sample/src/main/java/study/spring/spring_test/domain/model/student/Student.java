package study.spring.spring_test.domain.model.student;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
@Getter
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
