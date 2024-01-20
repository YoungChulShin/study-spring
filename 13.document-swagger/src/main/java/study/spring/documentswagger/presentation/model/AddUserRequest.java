package study.spring.documentswagger.presentation.model;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.context.annotation.Description;


@Getter
public class AddUserRequest {

    @Schema(name = "userName", description = "사용자 이름")
    @NotEmpty
    String userName;

    @Schema(name = "age", description = "나이")
    @Min(value = 20)
    @Max(value = 99)
    @NotNull
    Integer age;

    @Schema(name = "gender", description = "성별")
    @NotNull
    GenderDto gender;
}