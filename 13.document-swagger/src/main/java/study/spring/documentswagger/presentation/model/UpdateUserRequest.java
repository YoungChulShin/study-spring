package study.spring.documentswagger.presentation.model;

public record UpdateUserRequest(
    String userName,
    Integer age,
    GenderDto gender
) {
}