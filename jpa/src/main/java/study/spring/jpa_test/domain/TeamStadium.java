package study.spring.jpa_test.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team_stadium")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamStadium {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "team_id")
  private Team team;

  private String name;

  private String location;

  public TeamStadium(String name, String location) {
    this.name = name;
    this.location = location;
  }

  public void update(String name, String location) {
    this.name = name;
    this.location = location;
  }

  public void updateTeam(Team team) {
    this.team = team;
  }
}
