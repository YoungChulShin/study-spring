package study.spring.auth.bootserver.apprunner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import study.spring.auth.bootserver.domain.member.Member;
import study.spring.auth.bootserver.domain.member.MemberRole;
import study.spring.auth.bootserver.domain.member.MemberService;

@RequiredArgsConstructor
@Component
public class AppRunner implements ApplicationRunner {

    private final MemberService memberService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member = Member.builder()
                .email("test@test.com")
                .password("1234")
                .name("Test")
                .role(MemberRole.USER)
                .build();

        memberService.save(member);
    }
}
