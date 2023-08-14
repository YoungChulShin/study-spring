package study.spring.caller.adapter.out.systeminfo.openfeign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import study.spring.caller.application.port.in.model.SystemInfo;

//@Component
//@ConditionalOnProperty(value = "system-info.external-call.type", havingValue = "openfeign")
//public class OpenFeignFallback implements OpenFeignSystemInfo {
//
//  @Override
//  public SystemInfo getSystemInfo() {
//    return null;
//  }
//}

// TODO [ycshin]: fallback이 동작하지 않는 부분 수정 필요
@Component
@ConditionalOnProperty(value = "system-info.external-call.type", havingValue = "openfeign")
public class OpenFeignFallback implements FallbackFactory<OpenFeignSystemInfo> {

  @Override
  public OpenFeignSystemInfo create(Throwable cause) {
    return new OpenFeignSystemInfo() {

      @Override
      public SystemInfo getSystemInfo() {
        return null;
      }
    };
  }
}