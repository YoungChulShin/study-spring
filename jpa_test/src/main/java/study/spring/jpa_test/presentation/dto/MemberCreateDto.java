package study.spring.jpa_test.presentation.dto;

import lombok.Data;

@Data
public class MemberCreateDto {

    private String name;

    private Long teamId;
}
