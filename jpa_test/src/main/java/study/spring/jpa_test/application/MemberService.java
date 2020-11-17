package study.spring.jpa_test.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.jpa_test.domain.Member;
import study.spring.jpa_test.domain.MemberRepository;
import study.spring.jpa_test.domain.Team;
import study.spring.jpa_test.domain.TeamRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public Long save(String name, Long teamId) {

        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException());
        Member savedMember = memberRepository.save(Member.create(name, team));

        return savedMember.getId();
    }
}
