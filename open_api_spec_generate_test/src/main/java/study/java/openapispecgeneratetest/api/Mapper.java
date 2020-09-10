package study.java.openapispecgeneratetest.api;

import study.java.openapispecgeneratetest.api.model.MemberResponseDto;
import study.java.openapispecgeneratetest.api.model.MembersResponseDto;
import study.java.openapispecgeneratetest.entity.Member;

import java.util.List;

public final class Mapper {

    public static MemberResponseDto toMemberResponseDto(final Member member) {
        MemberResponseDto dto = new MemberResponseDto();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setAge(member.getAge());
        dto.setPhone(member.getPhone());
        dto.setGender(member.getGender().getValueString());

        return dto;
    }

    public static MembersResponseDto toMembersResponseDto(final List<Member> members) {
        MembersResponseDto dto = new MembersResponseDto();

        for (Member member : members) {
            dto.addDataItem(toMemberResponseDto(member));
        }

        return dto;
    }
}
