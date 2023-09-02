package study.spring.delivery.domain.delivery;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import study.spring.delivery.domain.delivery.model.CreateDeliveryCommand;

@Getter
@Entity
@Table(name = "deliveries")
public class Delivery {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "delivery_number")
  private String deliveryNumber;

  @Column(name = "order_number")
  private String orderNumber;

  @Column(name = "source_address")
  private String sourceAddress;

  @Column(name = "destination_address")
  private String destinationAddress;

  @Convert(converter = DeliveryStatusConverter.class)
  @Column(name = "status", columnDefinition = "TINYINT")
  private DeliveryStatus deliveryStatus;

  protected Delivery() {
  }

  public Delivery(CreateDeliveryCommand createRequest) {
    this.deliveryNumber = UUID.randomUUID().toString();
    this.orderNumber = createRequest.getOrderNumber();
    this.sourceAddress = createRequest.sourceAddress();
    this.destinationAddress = createRequest.destinationAddress();
    this.deliveryStatus = DeliveryStatus.CREATED;
  }

  public void start() {
    this.deliveryStatus = DeliveryStatus.STARTED;
  }

  public void complete() {
    this.deliveryStatus = DeliveryStatus.COMPLETED;
  }

  public void cancel() {
    this.deliveryStatus = DeliveryStatus.CANCELLED;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Delivery delivery = (Delivery) o;
    return Objects.equals(id, delivery.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Delivery.class);
  }

  @Override
  public String toString() {
    return "Delivery{" +
        "id=" + id +
        ", deliveryNumber=" + deliveryNumber +
        ", orderNumber=" + orderNumber +
        ", sourceAddress='" + sourceAddress + '\'' +
        ", destinationAddress='" + destinationAddress + '\'' +
        ", deliveryStatus=" + deliveryStatus +
        '}';
  }
}
