package study.backend.java.httprequestresponse.application.service;

import org.springframework.stereotype.Service;
import study.backend.java.httprequestresponse.application.port.in.HelloUseCase;
import study.backend.java.httprequestresponse.common.exceptions.InvalidParameterException;

@Service
public class HelloService implements HelloUseCase {

  @Override
  public String hello(String name) {
    if (name.equals("nothing")) {
      throw new InvalidParameterException("'nothing' is not allowed");
    }
    return "Hello, " + name;
  }
}
