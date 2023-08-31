package study.spring.delivery.domain.delivery;

import jakarta.persistence.AttributeConverter;

public class DeliveryStatusConverter implements AttributeConverter<DeliveryStatus, Integer> {


  @Override
  public Integer convertToDatabaseColumn(DeliveryStatus attribute) {
    return attribute.getValue();
  }

  @Override
  public DeliveryStatus convertToEntityAttribute(Integer dbData) {
    return DeliveryStatus.valueOf(dbData);
  }
}
