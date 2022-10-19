package study.spring.multipropertytest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Value("${application.hello}")
  private String hello;

  @Value("${application.bye}")
  private String bye;

  @GetMapping("/hello")
  public String hello() {
    return hello + " " + bye;
  }

}
