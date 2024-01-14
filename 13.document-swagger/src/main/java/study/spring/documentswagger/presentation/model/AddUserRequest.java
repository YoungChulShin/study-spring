package study.spring.documentswagger.presentation.model;

public record AddUserRequest(
    String userName,
    Integer age,
    GenderDto gender
) {
}