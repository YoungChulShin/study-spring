package study.spring.documentswagger.presentation.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record OrganizationDto(
    @Schema(name = "organizationCode", description = "기관 코드")
    @NotEmpty
    String organizationCode,

    @Schema(name = "organizationName", description = "기관 이름")
    @NotEmpty
    String organizationName
) {

}