package study.backend.java.httprequestresponse.application.port.in;

import jakarta.validation.constraints.NotNull;

public interface HelloUseCase {

  @NotNull
  String hello(@NotNull String name);
}
