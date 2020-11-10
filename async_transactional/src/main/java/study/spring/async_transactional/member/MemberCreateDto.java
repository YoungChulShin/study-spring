package study.spring.async_transactional.member;

import lombok.Data;

@Data
public class MemberCreateDto {

    private String name;

    private String teamName;    // 테스트를 위해서 넣어보자
}
