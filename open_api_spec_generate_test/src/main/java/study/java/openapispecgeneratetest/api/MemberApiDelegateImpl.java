package study.java.openapispecgeneratetest.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import study.java.openapispecgeneratetest.api.model.*;
import study.java.openapispecgeneratetest.entity.Member;
import study.java.openapispecgeneratetest.repository.MemberRepository;
import study.java.openapispecgeneratetest.service.MemberService;
import study.java.openapispecgeneratetest.service.command.CreateMemberRequestCommand;
import study.java.openapispecgeneratetest.service.command.UpdateMemberRequestCommand;

import java.util.List;

import static study.java.openapispecgeneratetest.config.Constants.HeaderKey.RESOURCE_ID;

@RequiredArgsConstructor
@Component
public class MemberApiDelegateImpl implements MemberApiDelegate {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Override
    public ResponseEntity<Void> createMember(CreateMemberRequestDto createMemberRequestDto) {

        Long savedMemberId = memberService.save(new CreateMemberRequestCommand(
                createMemberRequestDto.getName(),
                createMemberRequestDto.getAge(),
                createMemberRequestDto.getPhone(),
                createMemberRequestDto.getGender().getValue()
        ));

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(RESOURCE_ID, savedMemberId.toString())
                .build();
    }

    @Override
    public ResponseEntity<DeleteMemberResponseDto> deleteMember(Long memberId) {

        Long deletedMemberId = memberService.delete(memberId);

        DeleteMemberResponseDto responseDto = new DeleteMemberResponseDto();
        responseDto.setId(deletedMemberId);

        return ResponseEntity.ok().body(responseDto);
    }

    @Override
    public ResponseEntity<MemberResponseDto> getMember(Long memberId) {

        Member member = memberService.findMember(memberId);

        MemberResponseDto responseDto = Mapper.toMemberResponseDto(member);
        return ResponseEntity.ok().body(responseDto);
    }

    @Override
    public ResponseEntity<MembersResponseDto> getMembers() {

        List<Member> members = memberService.findMembers();

        MembersResponseDto responseDto = Mapper.toMembersResponseDto(members);
        return ResponseEntity.ok().body(responseDto);
    }

    @Override
    public ResponseEntity<MemberResponseDto> updateMember(Long memberId, UpdateMemberRequestDto updateMemberRequestDto) {

        Long updatedMemberId = memberService.update(
                new UpdateMemberRequestCommand(
                        memberId,
                        updateMemberRequestDto.getName(),
                        updateMemberRequestDto.getAge(),
                        updateMemberRequestDto.getPhone(),
                        updateMemberRequestDto.getGender().toString()));

        Member updatedMember = memberRepository.findById(updatedMemberId).get();
        MemberResponseDto responseDto = Mapper.toMemberResponseDto(updatedMember);

        return ResponseEntity.ok().body(responseDto);
    }
}
