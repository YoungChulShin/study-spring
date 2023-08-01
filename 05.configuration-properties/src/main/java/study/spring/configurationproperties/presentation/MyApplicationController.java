package study.spring.configurationproperties.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.configurationproperties.config.model.MyApplicationInfo;
import study.spring.configurationproperties.config.model.MyApplicationInfoV2;
import study.spring.configurationproperties.config.model.MyApplicationInfoV3;

@RestController
public class MyApplicationController {

  private final MyApplicationInfo myApplicationInfo;
  private final MyApplicationInfoV2 myApplicationInfoV2;
  private final MyApplicationInfoV3 myApplicationInfoV3;

  public MyApplicationController(
      MyApplicationInfo myApplicationInfo,
      MyApplicationInfoV2 myApplicationInfoV2,
      MyApplicationInfoV3 myApplicationInfoV3) {
    this.myApplicationInfo = myApplicationInfo;
    this.myApplicationInfoV2 = myApplicationInfoV2;
    this.myApplicationInfoV3 = myApplicationInfoV3;
  }

  @GetMapping("/my-application/v1/info")
  public MyApplicationInfo getInfo() {
    return this.myApplicationInfo;
  }

  @GetMapping("/my-application/v2/info")
  public MyApplicationInfoV2 getV2Info() {
    return this.myApplicationInfoV2;
  }

  @GetMapping("/my-application/v3/info")
  public MyApplicationInfoV3 getV3Info() {
    return this.myApplicationInfoV3;
  }
}
