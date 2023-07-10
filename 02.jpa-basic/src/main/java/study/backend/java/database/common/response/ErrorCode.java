package study.backend.java.database.common.response;

import lombok.Getter;

@Getter
public enum ErrorCode {

  COMMON_SYSTEM_ERROR("시스템 오류가 발생했습니다."),
  COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다.");

  private final String errorMessage;

  ErrorCode(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
