package study.spring.object_mapper_test;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {

  MAIL(10),
  FEMAIL(20);

  private int value;

  Gender(int value) {
    this.value = value;
  }

  @JsonValue
  public int getValue() {
    return value;
  }
}
