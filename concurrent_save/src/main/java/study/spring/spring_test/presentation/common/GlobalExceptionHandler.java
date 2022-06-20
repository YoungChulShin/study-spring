package study.spring.spring_test.presentation.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.spring.spring_test.service.exception.StudentExistException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(StudentExistException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String studentExistExceptionHandler(StudentExistException e) {
    return e.getMessage();
  }
}
