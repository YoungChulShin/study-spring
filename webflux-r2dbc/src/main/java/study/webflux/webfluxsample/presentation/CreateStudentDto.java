package study.webflux.webfluxsample.presentation;

import study.webflux.webfluxsample.domain.Gender;

public record CreateStudentDto(
        String name,
        int age,
        Gender gender,
        String schoolName
) {
}
