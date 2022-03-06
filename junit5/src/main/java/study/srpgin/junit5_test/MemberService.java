package study.srpgin.junit5_test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.srpgin.junit5_test.commands.MemberSaveCommand;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long saveOne(MemberSaveCommand memberSaveCommand) {

        Member member = memberSaveCommand.toEntity();
        memberRepository.save(member);

        return member.getId();
    }
}
