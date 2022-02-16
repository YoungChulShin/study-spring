package study.spring.retry_transactional.domain;

import lombok.Getter;

@Getter
public class OrderInfo {

  private String orderNumber;
  private int version;

  public OrderInfo(Order order) {
    this.orderNumber = order.getOrderNumber();
    this.version = order.getVersion();
  }
}
