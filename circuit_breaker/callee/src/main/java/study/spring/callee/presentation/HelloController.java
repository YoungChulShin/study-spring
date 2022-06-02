package study.spring.callee.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/greeting/hello/{name}")
  public String hello(@PathVariable(value = "name") String name) throws InterruptedException {
    //Thread.sleep(10000);
    return "hello, " + name;
  }
}
