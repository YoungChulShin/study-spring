package study.backend.java.httprequestresponse.application.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import study.backend.java.httprequestresponse.application.port.in.HelloUseCase;
import study.backend.java.httprequestresponse.common.exceptions.InvalidParameterException;

@Validated
@Service
public class HelloService implements HelloUseCase {

  @NotNull
  @Override
  public String hello(@NotNull String name) {
    Assert.notNull(name, "name은 null일 수 없습니다");

    if (name.equals("nothing")) {
      throw new InvalidParameterException("'nothing' is not allowed");
    }
    return "Hello, " + name;
  }
}
