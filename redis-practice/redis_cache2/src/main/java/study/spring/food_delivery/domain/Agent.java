package study.spring.food_delivery.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private int age;

  @Column(name = "delivery_sum")
  private int deliverySum;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "agent_location_id")
  private AgentLocation location;

  public Agent(String name, int age) {
    this.name = name;
    this.age = age;
    this.deliverySum = 0;
    this.location = new AgentLocation(null, null);
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
}
