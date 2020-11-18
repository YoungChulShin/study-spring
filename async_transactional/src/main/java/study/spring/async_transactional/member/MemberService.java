package study.spring.async_transactional.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.async_transactional.common.MemberCreatedEvent;
import study.spring.async_transactional.team.TeamService;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamService teamService;

    private final ApplicationEventPublisher eventPublisher;

    public Long saveOne(String name, String teamName) {

        log.info("MemberService - start save");
        Member member = Member.create(name);
        Member savedMember = memberRepository.save(member);
        log.info("MemberService - end save");

        memberRepository.findById(1L);

        return savedMember.getId();
    }

    @Transactional
    public Long saveOneWithEvent(String name, String teamName) {

        log.info("MemberService - start save : " + Thread.currentThread().getId());
        Member member = Member.create(name);
        Member savedMember = memberRepository.save(member);
        log.info("MemberService - end save : " + Thread.currentThread().getId());

        if (1 == 1)


        log.info("Event - call start : " + Thread.currentThread().getId());
        eventPublisher.publishEvent(new MemberCreatedEvent(savedMember.getId(), teamName));
        log.info("Event - call end : " + Thread.currentThread().getId());
        return savedMember.getId();
    }
}
