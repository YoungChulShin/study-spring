package study.spring.caller.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.spring.caller.service.HelloService;

@RestController
public class HelloController {

  private final HelloService helloService;

  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  @GetMapping("/greeting/hello/{name}")
  public String hello(@PathVariable(value = "name") String name) {
    return helloService.hello(name);
  }
}
