package study.spring.spring_test.service.exception;

public class StudentExistException extends RuntimeException {

  public StudentExistException(String message) {
    super(message);
  }
}
