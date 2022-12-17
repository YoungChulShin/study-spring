package study.webflux.webfluxsample.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    private Long id;

    private String name;

    private int age;

    private Gender gender;

    private Long schoolId;

    public Student(String name, int age, Gender gender, Long schoolId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.schoolId = schoolId;
    }
}
