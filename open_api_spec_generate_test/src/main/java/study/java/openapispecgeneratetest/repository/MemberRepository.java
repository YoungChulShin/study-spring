package study.java.openapispecgeneratetest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.java.openapispecgeneratetest.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
