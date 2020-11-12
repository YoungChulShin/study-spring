package study.spring.jpa_envers;

import java.util.List;

public interface CustomMemberRepository {

    List<Member> findByMemberId(Long memberId);
}
