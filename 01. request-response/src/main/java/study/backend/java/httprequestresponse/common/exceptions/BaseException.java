package study.backend.java.httprequestresponse.common.exceptions;

import lombok.Getter;
import study.backend.java.httprequestresponse.common.response.ErrorCode;

@Getter
public class BaseException extends RuntimeException {

  private ErrorCode errorCode;

  public BaseException() { }

  public BaseException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public BaseException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public BaseException(String message, ErrorCode errorCode, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }
}
