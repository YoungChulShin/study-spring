package study.spring.documentswagger.presentation.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class AddUserRequest {

    @NotEmpty
    String userName;

    @Min(value = 20)
    @Max(value = 99)
    @NotNull
    Integer age;

    @NotNull
    GenderDto gender;
}