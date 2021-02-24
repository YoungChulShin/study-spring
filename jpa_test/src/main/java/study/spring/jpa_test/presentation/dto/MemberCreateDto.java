package study.spring.jpa_test.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class MemberCreateDto {

    @NotNull
    public String name;

    public Long teamId;
}
