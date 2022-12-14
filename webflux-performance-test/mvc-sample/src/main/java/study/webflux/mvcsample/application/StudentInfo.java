package study.webflux.mvcsample.application;

import study.webflux.mvcsample.domain.Gender;
import study.webflux.mvcsample.domain.Student;

public record StudentInfo(
        Long id,
        String name,
        int age,
        Gender gender,
        String schoolName
) {

    public static StudentInfo from(Student student) {
        return new StudentInfo(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getGender(),
                student.getSchoolName()
        );
    }
}
