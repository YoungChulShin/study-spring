package study.spring.async_transactional.common;

import lombok.Getter;

@Getter
public class MemberCreatedEvent {

    private Long memberId;

    private String teamName;

    public MemberCreatedEvent(Long memberId, String teamName) {
        this.memberId = memberId;
        this.teamName = teamName;
    }
}
