package study.spring.jpa_test.application;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import study.spring.jpa_test.domain.Member;
import study.spring.jpa_test.domain.MemberRepository;
import study.spring.jpa_test.domain.Team;
import study.spring.jpa_test.domain.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(String name) {
        Team savedTeam = teamRepository.save(Team.create(name));
        return savedTeam.getId();
    }

    @Transactional
    public boolean isMyTeamMember(Long teamId, List<Long> memberIds) {

//        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException());
//
//        List<Long> collect = memberIds.stream().filter(memberId -> team.isMyTeam(memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException()))).collect(Collectors.toList());
//
//        memberRepository.findById(5L).orElseGet(null);
//
//        Team team2 = teamRepository.findById(2L).orElseThrow(() -> new IllegalArgumentException());
//        memberRepository.findByTeam(team2);
//
//        memberRepository.findById(6L).orElseGet(null);
//
//        teamTest();
//
//        return collect.size() == memberIds.size();

        memberRepository.findById(1L);

        TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
        int i = status.hashCode();

        teamTest();



        return true;
    }

    @Transactional
    public void updateStadium(Long teamId, String name, String location) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (!team.isPresent()) {
            return;
        }

        team.get().AddStadium(name, location);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public void teamTest() {

        TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();
        int i = status.hashCode();

        memberRepository.findById(1L).orElseGet(null);
        memberRepository.findById(2L).orElseGet(null);
    }
}
