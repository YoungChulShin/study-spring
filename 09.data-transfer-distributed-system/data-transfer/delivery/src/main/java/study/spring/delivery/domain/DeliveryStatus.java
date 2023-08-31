package study.spring.delivery.domain;

import java.util.Arrays;

public enum DeliveryStatus {

  CREATED(10),
  STARTED(20),
  COMPLETED(30),
  CANCELLED(40)
  ;

  private final int value;

  DeliveryStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static DeliveryStatus valueOf(int value) {
    return Arrays.stream(DeliveryStatus.values())
        .filter(deliveryStatus -> deliveryStatus.value == value)
        .findFirst()
        .orElseThrow(() -> new RuntimeException("deliveryStatus를 찾을 수 없습니다"));
  }
}
