package study.spring.querydsl.repository;

import study.spring.querydsl.dto.MemberSearchCondition;
import study.spring.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSearchCondition condition);
}
