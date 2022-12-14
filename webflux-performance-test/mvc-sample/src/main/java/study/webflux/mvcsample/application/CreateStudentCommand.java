package study.webflux.mvcsample.application;

import study.webflux.mvcsample.domain.Gender;

public record CreateStudentCommand(
        String name,
        int age,
        Gender gender,
        String schoolName
) {

}
