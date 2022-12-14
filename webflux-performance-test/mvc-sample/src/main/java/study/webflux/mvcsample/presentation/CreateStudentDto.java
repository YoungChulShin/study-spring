package study.webflux.mvcsample.presentation;

import study.webflux.mvcsample.domain.Gender;

public record CreateStudentDto(
        String name,
        int age,
        Gender gender,
        String schoolName
) {
}
