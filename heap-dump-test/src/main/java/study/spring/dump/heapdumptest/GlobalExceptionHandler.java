package study.spring.dump.heapdumptest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = OutOfMemoryError.class)
  public void handleOutOfMemory(OutOfMemoryError error) {
    System.out.println("Handle OutOfMemory");
    System.out.println("Max memory: " + Runtime.getRuntime().maxMemory());
    System.out.println("Total memory: " + Runtime.getRuntime().totalMemory());
    System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());

  }

}
