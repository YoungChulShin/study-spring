package study.spring.callee.presentation;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

  private final Logger logger = LoggerFactory.getLogger(SystemController.class);
  private AtomicInteger callCount = new AtomicInteger(0);

  @GetMapping("/api/v1/system")
  public SystemInfo getSystemInfo() {
    if (callCount.incrementAndGet() % 5 == 0) {
      throw new RuntimeException("에러가 발생했습니다");
    }

    SystemInfo systemInfo = new SystemInfo(
        "Callee 시스템",
        "5번에 1번씩 에러를 응답하는 Callee 시스템입니다",
        "0.99.1",
        "대박컴퍼니",
        2023,
        8);
    logger.info(systemInfo.toString());

    return systemInfo;
  }
}
