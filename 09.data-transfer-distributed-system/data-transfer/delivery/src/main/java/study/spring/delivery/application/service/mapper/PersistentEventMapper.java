package study.spring.delivery.application.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.spring.delivery.application.service.dto.persistentevent.DeliveryStatusChangedPersistentEventDto;
import study.spring.delivery.domain.delivery.Delivery;
import study.spring.delivery.domain.persistentevent.PersistentEvent;
import study.spring.delivery.domain.persistentevent.PersistentEventType;

@Component
@RequiredArgsConstructor
public class PersistentEventMapper {

  private final ObjectMapper objectMapper;

  public PersistentEvent toEntity(PersistentEventType eventType, Delivery delivery) {
    var eventDto = new DeliveryStatusChangedPersistentEventDto(delivery);
    String body;
    try {
      body = objectMapper.writeValueAsString(eventDto);
    } catch (Exception e) {
      throw new RuntimeException("변환 실패", e);
    }
    return new PersistentEvent(eventDto.getEventType(), eventDto.getEventId(), body);
  }
}
