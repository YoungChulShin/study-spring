package study.spring.delivery.application.service.dto.persistentevent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import study.spring.delivery.domain.persistentevent.PersistentEventType;

@Getter
public abstract class BasePersistentEventDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @JsonProperty("eventId")
  private final UUID eventId;

  @JsonProperty("eventType")
  private final PersistentEventType eventType;

  @JsonProperty("timestamp")
  private final Long timestamp;

  public BasePersistentEventDto(PersistentEventType eventType) {
    this.eventId = UUID.randomUUID();
    this.eventType = eventType;
    this.timestamp = System.currentTimeMillis();
  }
}
