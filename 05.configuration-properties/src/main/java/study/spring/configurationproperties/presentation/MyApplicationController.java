package study.spring.configurationproperties.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.configurationproperties.config.model.MyApplicationInfo;
import study.spring.configurationproperties.config.model.MyApplicationInfoV2;

@RestController
public class MyApplicationController {

  private final MyApplicationInfo myApplicationInfo;
  private final MyApplicationInfoV2 myApplicationInfoV2;

  public MyApplicationController(
      MyApplicationInfo myApplicationInfo,
      MyApplicationInfoV2 myApplicationInfoV2) {
    this.myApplicationInfo = myApplicationInfo;
    this.myApplicationInfoV2 = myApplicationInfoV2;
  }

  @GetMapping("/my-application/v1/info")
  public MyApplicationInfo getInfo() {
    return this.myApplicationInfo;
  }

  @GetMapping("/my-application/v2/info")
  public MyApplicationInfoV2 getV2Info() {
    return this.myApplicationInfoV2;
  }
}
