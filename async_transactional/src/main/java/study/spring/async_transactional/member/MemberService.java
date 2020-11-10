package study.spring.async_transactional.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.async_transactional.team.TeamService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final TeamService teamService;

    @Transactional
    public Long saveOne(String name, String teamName) {

        log.info("MemberService - start save");
        Member member = Member.create(name);
        Member savedMember = memberRepository.save(member);
        log.info("MemberService - end save");

        teamService.saveOne(teamName);

        log.info("MemberService - call save");
        return savedMember.getId();
    }
}
