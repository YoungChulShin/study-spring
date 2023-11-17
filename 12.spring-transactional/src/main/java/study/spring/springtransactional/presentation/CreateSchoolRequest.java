package study.spring.springtransactional.presentation;

import jakarta.validation.constraints.NotNull;

public record CreateSchoolRequest(
    @NotNull String name) {

}
