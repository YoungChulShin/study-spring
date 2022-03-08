package study.spring.food_delivery.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "agents")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agent {

  @EmbeddedId
  private AgentId id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private int age;

  @Column(name = "delivery_sum")
  private int deliverySum;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agent_location_id")
  private AgentLocation location;

  public Agent(String name, int age) {
    this.name = name;
    this.age = age;
    this.deliverySum = 0;
  }

  public void updateLocation(double longitude, double latitude) {
    if (this.location == null) {
      this.location = new AgentLocation(longitude, latitude);
    } else {
      this.location.updateLocation(longitude, latitude);
    }
  }

  public void updateName(String name) {
    this.name = name;
  }

  public void updateAge(int age) {
    this.age = age;
  }

  public int delivery() {
    this.deliverySum += 1;
    return this.deliverySum;
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class AgentId implements Serializable {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public AgentId(Long id) {
      this.id = id;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      AgentId agentId = (AgentId) o;
      return Objects.equals(id, agentId.id);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }
  }
}
