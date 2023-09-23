package study.backend.java.database.common.response;

import lombok.Getter;

@Getter
public class CommonResponse<T> {

  private final Result result;

  private final T data;

  private final Integer page;
  private final Integer size;
  private final Integer totalElements;
  private final Integer totalPages;

  private final String errorMessage;
  private final String errorCode;

  private CommonResponse(
      Result result,
      T data,
      String errorMessage,
      String errorCode) {
    this.result = result;
    this.data = data;

    boolean hasPage = data instanceof PageDto;
    this.page = hasPage ? ((PageDto) data).page() : null;
    this.size = hasPage ? ((PageDto) data).size(): null;
    this.totalElements = hasPage ? ((PageDto) data).totalElements() : null;
    this.totalPages = hasPage ? ((PageDto) data).totalPages() : null;

    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }

  private CommonResponse(
      Result result,
      T  data,
      PageDto pageInfo,
      String errorMessage,
      String errorCode) {
    this.result = result;
    this.data = data;
    this.page = pageInfo != null ? pageInfo.page() : null;
    this.size = pageInfo != null ? pageInfo.size() : null;
    this.totalElements = pageInfo != null ? pageInfo.totalElements() : null;
    this.totalPages = pageInfo != null ? pageInfo.totalPages() : null;
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }

  public static <T> CommonResponse<T> success(T data) {
    return new CommonResponse<>(
        Result.SUCCESS,
        data,
        null,
        null);
  }

  public static <T> CommonResponse<T> success(T data, PageDto pageInfo) {
    return new CommonResponse<>(
        Result.SUCCESS,
        data,
        pageInfo,
        null,
        null);
  }

  public static <T> CommonResponse<T> success() {
    return CommonResponse.success(null);
  }

  public static <T> CommonResponse<T> fail(String errorMessage, String errorCode) {
    return new CommonResponse<>(
        Result.FAIL,
        null,
        errorMessage,
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
