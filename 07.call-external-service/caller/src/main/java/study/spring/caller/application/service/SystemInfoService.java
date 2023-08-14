package study.spring.caller.application.service;

import org.springframework.stereotype.Service;
import study.spring.caller.application.port.in.SystemInfoUseCase;
import study.spring.caller.application.port.in.model.SystemInfo;
import study.spring.caller.application.port.out.SystemInfoPort;

@Service
class SystemInfoService implements SystemInfoUseCase {

  private final SystemInfoPort systemInfoPort;

  public SystemInfoService(SystemInfoPort systemInfoPort) {
    this.systemInfoPort = systemInfoPort;
  }

  @Override
  public SystemInfo getSystemInfo() {
    return systemInfoPort.getSystemInfo();
  }
}
