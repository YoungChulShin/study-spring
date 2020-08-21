package study.spring.auth.bootserver.web.dto.member;

import lombok.Getter;
import lombok.Setter;
import study.spring.auth.bootserver.domain.member.Member;
import study.spring.auth.bootserver.domain.member.MemberRole;

@Getter
public class MemberResponseDto {

    private String email;
    private String password;
    private String name;
    private MemberRole role;

    public MemberResponseDto(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.role = member.getRole();
    }
}
