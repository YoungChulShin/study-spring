package study.webflux.webfluxsample.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "students")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    private Long id;

    private String name;

    private int age;

    private Gender gender;

    private Long schoolId;

    @Transient
    private School school;

    public Student(String name, int age, Gender gender, School school) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.schoolId = school.getId();
        this.school = school;
    }
}
