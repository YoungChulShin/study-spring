package study.spring.caller.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.caller.application.port.in.SystemInfoUseCase;
import study.spring.caller.application.port.in.model.SystemInfo;

@RestController
class SystemController {

  private final SystemInfoUseCase systemInfoUseCase;

  public SystemController(SystemInfoUseCase systemInfoUseCase) {
    this.systemInfoUseCase = systemInfoUseCase;
  }

  @GetMapping("/api/system-info")
  public SystemInfo getSystemInfo() {
    return systemInfoUseCase.getSystemInfo();
  }
}
