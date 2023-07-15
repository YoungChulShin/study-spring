package study.backend.java.httprequestresponse.adapter.in.web.model;

import jakarta.validation.constraints.NotEmpty;

public record AddressDto(
    @NotEmpty String fullAddress,
    @NotEmpty String bunji,
    @NotEmpty String roadName) {
}
