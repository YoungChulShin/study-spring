package study.backend.java.database.common.response;

import lombok.Getter;

@Getter
public class CommonResponse<T> {

  private final Result result;
  private final T data;
  private final String message;
  private final String errorCode;

  private CommonResponse(Result result, T data, String message, String errorCode) {
    this.result = result;
    this.data = data;
    this.message = message;
    this.errorCode = errorCode;
  }

  public static <T> CommonResponse<T> success(T data, String message) {
    return new CommonResponse<>(
        Result.SUCCESS,
        data,
        message,
        null);
  }

  public static <T> CommonResponse<T> success(T data) {
    return CommonResponse.success(data, null);
  }

  public static <T> CommonResponse<T> success() {
    return CommonResponse.success(null, null);
  }

  public static <T> CommonResponse<T> fail(String message, String errorCode) {
    return new CommonResponse<>(
        Result.FAIL,
        null,
        message,
        errorCode);
  }

  public static <T> CommonResponse<T> fail(ErrorCode errorCode) {
    return new CommonResponse<>(
        Result.FAIL,
        null,
        errorCode.getErrorMessage(),
        errorCode.name());
  }

  public enum Result {
    SUCCESS,
    FAIL
  }

}
