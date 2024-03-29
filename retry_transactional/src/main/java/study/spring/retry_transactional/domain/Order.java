package study.spring.retry_transactional.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.spring.retry_transactional.infrastructure.OrderRepository;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String orderNumber;

  @Version
  private int version;

  public Order(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public void updateOrderNumber(String orderNumber) {
    if (orderNumber == null || orderNumber.length() == 0) {
      throw new IllegalArgumentException("orderNumber");
    }

    this.orderNumber = orderNumber;
  }
}
