package study.backend.java.httprequestresponse.adapter.in.web.model;

public record HelloRememberResponse(
    String name,
    Integer age,
    AddressDto address
) {

}
