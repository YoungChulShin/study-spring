package study.backend.java.httprequestresponse.common.response;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.backend.java.httprequestresponse.common.exceptions.BaseException;
import study.backend.java.httprequestresponse.common.exceptions.InvalidParameterException;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

  private static final List<ErrorCode> SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST = new ArrayList<>();

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {InvalidParameterException.class})
  public CommonResponse handleInvalidParameterException(InvalidParameterException e) {
    return CommonResponse.fail(e.getMessage(), e.getErrorCode().name());
  }

  @ResponseStatus(value = HttpStatus.OK)
  @ExceptionHandler(value = {BaseException.class})
  public CommonResponse handleBaseException(BaseException e) {
    if (SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST.contains(e.getErrorCode())) {
      log.warn("Base error: " + e.getMessage(), e);
    } else {
      log.warn("Base warn: " + e.getMessage(), e);
    }

    return CommonResponse.fail(e.getMessage(), e.getErrorCode().name());
  }

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = {Exception.class})
  public CommonResponse handleException(Exception e) {
    log.error("System error: " + e.getMessage(), e);
    return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR);
  }

  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(value = {ClientAbortException.class})
  public CommonResponse handleClientAbort(Exception e) {
    log.warn("Skip warn: " + e.getMessage(), e);
    return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public CommonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    BindingResult bindingResult = e.getBindingResult();
    FieldError fe = bindingResult.getFieldError();
    if (fe != null) {
      String message =
          "Request Error" + " " + fe.getField() + "=" + fe.getRejectedValue() + " (" + fe
              .getDefaultMessage() + ")";
      return CommonResponse.fail(message, ErrorCode.COMMON_INVALID_PARAMETER.name());
    } else {
      return CommonResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER);
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = {MissingServletRequestParameterException.class})
  public CommonResponse handleMissingServletRequestParameter(MissingServletRequestParameterException e) {
    String message = "Request parameter '" + e.getParameterName() + "' is not present";
    log.error("Invalid request: " + message);

    return CommonResponse.fail(message, ErrorCode.COMMON_INVALID_PARAMETER.name());
  }
}
