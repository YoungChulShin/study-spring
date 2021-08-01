package study.spring.jpa_test.domain;

import java.util.Collections;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
    
    @ElementCollection
    @CollectionTable(
        name = "team_stadium",
        joinColumns = @JoinColumn(name = "team_id"))
    private List<TeamStadium> teamStadiums = Collections.emptyList();

    @Version
    private int version;
    public static Team create(String name) {
        Team team = new Team();
        team.name = name;

        return team;
    }

    public void AddStadium(String name, String location) {
        if (this.teamStadiums.isEmpty()) {
            this.teamStadiums.add(new TeamStadium(name, location));
        } else {
            this.teamStadiums.set(0, new TeamStadium(name, location));
        }
    }

    public boolean isMyTeam(Member member) {
        return members.stream().anyMatch(x -> x.getId().equals(member.getId()));
    }
}
