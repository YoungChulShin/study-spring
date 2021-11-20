package study.spring.async_thread.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  @Async
  public void testMethodWithSleep(int sleepSeeconds) {
    System.out.println("");
    Thread.currentThread().getName()
  }

}
