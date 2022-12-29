package study.webflux.webfluxsample.application;

import study.webflux.webfluxsample.domain.Gender;

public record CreateStudentCommand(
        String name,
        int age,
        Gender gender,
        String schoolName
) {

}
