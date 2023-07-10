package study.backend.java.httprequestresponse.common.exceptions;

import study.backend.java.httprequestresponse.common.response.ErrorCode;

public class InvalidParameterException extends BaseException {

  public InvalidParameterException() {
    super(ErrorCode.COMMON_INVALID_PARAMETER);
  }

  public InvalidParameterException(String errorMessage) {
    super(errorMessage, ErrorCode.COMMON_INVALID_PARAMETER);
  }
}
