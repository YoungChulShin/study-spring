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

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "team",
        cascade = CascadeType.PERSIST,
        orphanRemoval = true)
    private List<Member> members = new ArrayList<>();
    @OneToMany(
        mappedBy = "team",
        fetch = FetchType.LAZY,
        cascade = CascadeType.PERSIST,
        orphanRemoval = true)
    private List<TeamStadium> teamStadiums = new ArrayList<>();

    @Version
    private int version;


    public static Team create(String name) {
        Team team = new Team();
        team.name = name;
        return team;
    }

    public void AddStadium(TeamStadium teamStadium) {
        teamStadium.updateTeam(this);
        if (this.teamStadiums.isEmpty()) {
            this.teamStadiums.add(teamStadium);
        } else {
            this.teamStadiums.set(0, teamStadium);
        }
    }

    public boolean isMyTeam(Member member) {
        return members.stream().anyMatch(x -> x.getId().equals(member.getId()));
    }
}
