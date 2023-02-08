package study.spring.dump.heapdumptest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final MemoryTestService testService;

  @PostMapping("/heap-memory-test")
  public String heapMemoryTest() {
    testService.test();


    return "done";
  }
}