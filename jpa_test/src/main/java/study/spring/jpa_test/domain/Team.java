package study.spring.jpa_test.domain;

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

    public static Team create(String name) {
        Team team = new Team();
        team.name = name;

        return team;
    }

    public boolean isMyTeam(Member member) {

        boolean containMember = members.stream().anyMatch(x -> x.getId().equals(member.getId()));

        return containMember;
    }
}
