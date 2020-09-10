package study.java.openapispecgeneratetest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.java.openapispecgeneratetest.entity.Member;
import study.java.openapispecgeneratetest.repository.MemberRepository;
import study.java.openapispecgeneratetest.service.command.CreateMemberRequestCommand;
import study.java.openapispecgeneratetest.service.command.UpdateMemberRequestCommand;
import study.java.openapispecgeneratetest.service.exception.MemberNotFoundException;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(CreateMemberRequestCommand command) {
        Member member = command.toEntity();

        memberRepository.save(member);
        return member.getId();
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("사용자를 찾을 수 없습니다", id));
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Long update(UpdateMemberRequestCommand command) {
        Member member = memberRepository.findById(command.getId())
                .orElseThrow(() -> new MemberNotFoundException("사용자를 찾을 수 없습니다", command.getId()));

        member.update(command.getName(), command.getAge(), command.getPhone(), command.getGender());

        return member.getId();
    }

    @Transactional
    public Long delete(Long id) {
        memberRepository.deleteById(id);

        return id;
    }
}