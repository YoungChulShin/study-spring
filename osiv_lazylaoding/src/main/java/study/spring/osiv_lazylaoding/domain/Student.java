package study.spring.osiv_lazylaoding.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Long age;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private School school;

  public String getSchoolName() {
    return school.getName();
  }

  public Student(String name, Long age) {
    this.name = name;
    this.age = age;
  }

  void registerSchool(School school) {
    this.school = school;
  }
}
