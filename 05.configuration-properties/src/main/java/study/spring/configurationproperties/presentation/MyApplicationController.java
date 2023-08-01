package study.spring.configurationproperties.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.configurationproperties.config.model.MyApplicationInfo;

@RestController
public class MyApplicationController {

  private final MyApplicationInfo myApplicationInfo;

  public MyApplicationController(MyApplicationInfo myApplicationInfo) {
    this.myApplicationInfo = myApplicationInfo;
  }

  @GetMapping("/my-application/info")
  public MyApplicationInfo getInfo() {
    return this.myApplicationInfo;
  }
}
