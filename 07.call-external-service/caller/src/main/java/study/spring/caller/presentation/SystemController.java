package study.spring.caller.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.caller.application.model.SystemInfo;
import study.spring.caller.application.v1.SystemInfoServiceV1;

@RestController
public class SystemController {

  private final SystemInfoServiceV1 systemInfoServiceV1;

  public SystemController(SystemInfoServiceV1 systemInfoServiceV1) {
    this.systemInfoServiceV1 = systemInfoServiceV1;
  }

  @GetMapping("/api/v1/system-info")
  public SystemInfo getSystemInfoV1() {
    return systemInfoServiceV1.getSystemInfo();
  }
}
