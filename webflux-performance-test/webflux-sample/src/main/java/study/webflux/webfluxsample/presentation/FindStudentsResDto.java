package study.webflux.webfluxsample.presentation;

import study.webflux.webfluxsample.domain.StudentInfo;

import java.util.List;

public record FindStudentsResDto(
        List<StudentInfo> students
) {
}
