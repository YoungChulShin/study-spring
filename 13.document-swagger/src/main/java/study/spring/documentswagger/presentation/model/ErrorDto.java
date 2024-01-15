package study.spring.documentswagger.presentation.model;

import lombok.Getter;

@Getter
public class ErrorDto {

  private final String errorCode;

  public ErrorDto(String errorCode) {
    this.errorCode = errorCode;
  }
}
