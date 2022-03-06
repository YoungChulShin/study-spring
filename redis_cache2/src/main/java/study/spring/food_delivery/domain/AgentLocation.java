package study.spring.food_delivery.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "agents_location")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgentLocation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "longitude")
  private Double longitude;

  @Column(name = "latitude")
  private Double latitude;

  public AgentLocation(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  void updateLocation(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }
}
