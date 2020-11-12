package study.spring.jpa_envers;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberRevisionsResponseDto {

    private final List<MemberRevisionResponseDto> memberRevisionResponseDtos = new ArrayList<>();

    public MemberRevisionsResponseDto(List<Member> members) {
        for (Member member : members) {
            MemberRevisionResponseDto memberRevisionDto = new MemberRevisionResponseDto(member.getId(), member.getName(), member.getAge(), member.getPhone());
            memberRevisionResponseDtos.add(memberRevisionDto);
        }
    }

    @Data
    static class MemberRevisionResponseDto {

        private Long id;

        private String name;

        private int age;

        private String phone;

        public MemberRevisionResponseDto(Long id, String name, int age, String phone) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.phone = phone;
        }
    }
}
