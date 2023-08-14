package study.spring.caller.adapter.out.systeminfo.openfeign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import study.spring.caller.application.port.in.model.SystemInfo;
import study.spring.caller.application.port.out.SystemInfoPort;

@FeignClient(
    value = "systeminfo",
    url = "http://localhost:8080",
    fallbackFactory = OpenFeignFallback.class
)
@ConditionalOnProperty(value = "system-info.external-call.type", havingValue = "openfeign")
public interface OpenFeignSystemInfo extends SystemInfoPort {

  @Override
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/api/v1/system")
  SystemInfo getSystemInfo();
}
