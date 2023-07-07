package study.backend.java.httprequestresponse.adapter.in.web.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record HelloRememberRequest(
    @NotEmpty
    String name,

    @NotNull @Min(0) @Max(100)
    Integer age,

    @Valid @NotNull
    AddressDto address
) {

  public record AddressDto(
      @NotEmpty String fullAddress,
      @NotEmpty String bunji,
      @NotEmpty String roadName) {
  }
}
