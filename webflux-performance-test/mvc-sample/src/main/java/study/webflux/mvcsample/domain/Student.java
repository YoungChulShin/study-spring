package study.webflux.mvcsample.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    private School school;

    public Student(String name, int age, Gender gender, School school) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.school = school;

        this.school.addStudent(this);
    }

    public String getSchoolName() {
        return this.school.getName();
    }
}
