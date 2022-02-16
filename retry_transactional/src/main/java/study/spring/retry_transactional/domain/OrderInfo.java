package study.spring.retry_transactional.domain;

import lombok.Getter;

@Getter
public class OrderInfo {

  private String orderNumber;
  private int version;

  public OrderInfo(String orderNumber, int version) {
    this.orderNumber = orderNumber;
    this.version = version;
  }
}
