package study.spring.jpa_envers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CustomMemberRepository customMemberRepository;

    @Transactional
    public Long save(String name, int age, String phone) {
        Member savedMember = memberRepository.save(Member.create(name, age, phone));
        return savedMember.getId();
    }

    @Transactional
    public void update(Long id, String name, int age, String phone) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        findMember.update(name, age, phone);
    }

    public List<Member> getRevisions(Long id) {
        return customMemberRepository.findByMemberId(id);
    }
}
