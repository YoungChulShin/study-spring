package study.spring.jmeter.blocking.blockingtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/test")
  public String test() throws InterruptedException {
    Thread.sleep(500);
    return "test";
  }
}
