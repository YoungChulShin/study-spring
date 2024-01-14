package study.spring.documentswagger.presentation.model;

public record UserDto(
    Long id,
    String userName,
    Integer age,
    GenderDto gender
) {
}
