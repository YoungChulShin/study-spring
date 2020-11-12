package study.spring.jpa_envers;

import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    private final EntityManager em;

    @Override
    public List<Member> findByMemberId(Long memberId) {

        AuditReader auditReader = AuditReaderFactory.get(em);

        List<Member> revisionList = auditReader.createQuery().forRevisionsOfEntity(Member.class, true, true)
                .add(AuditEntity.id().eq(memberId))
                .getResultList();

        return revisionList;
    }
}
