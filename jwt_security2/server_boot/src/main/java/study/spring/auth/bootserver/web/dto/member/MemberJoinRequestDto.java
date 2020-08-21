package study.spring.auth.bootserver.web.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.spring.auth.bootserver.domain.member.Member;
import study.spring.auth.bootserver.domain.member.MemberRole;

@Setter
@Getter
public class MemberJoinRequestDto {

    private String email;
    private String password;
    private String name;
    private MemberRole role;

    public MemberJoinRequestDto(String email, String password, String name, MemberRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .role(role)
                .build();
    }
}
