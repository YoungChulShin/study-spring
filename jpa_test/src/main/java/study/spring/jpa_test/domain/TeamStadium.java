package study.spring.jpa_test.domain;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamStadium {

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
}
