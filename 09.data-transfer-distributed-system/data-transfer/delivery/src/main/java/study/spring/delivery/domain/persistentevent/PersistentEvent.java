package study.spring.delivery.domain.persistentevent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;

@Entity
@Table(name = "persistent_events")
@Getter
public class PersistentEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "event_type", nullable = false)
  private PersistentEventType eventType;

  @Column(name = "event_id", nullable = false)
  private UUID eventID;

  @Column(name = "body", nullable = false)
  private String body;

  @Column(name = "status", nullable = false)
  private PersistentEventStatus status;

  @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
  private Instant createdAt;

  @Column(name = "published_at", columnDefinition = "TIMESTAMP")
  private Instant publishedAt;

  protected PersistentEvent() {
  }

  public PersistentEvent(PersistentEventType eventType, UUID eventID, String body) {
    this.eventType = eventType;
    this.eventID = eventID;
    this.body = body;
    this.status = PersistentEventStatus.CREATED;
    this.createdAt = Instant.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersistentEvent that = (PersistentEvent) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(PersistentEvent.class);
  }
}
