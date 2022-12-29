package study.webflux.webfluxsample.domain;

public record StudentInfo(
        Long id,
        String name,
        int age,
        Gender gender,
        String schoolName
) {

    public static StudentInfo from(Student student, String schoolName) {
        return new StudentInfo(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getGender(),
                schoolName
        );
    }
}
