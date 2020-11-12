package study.spring.jpa_envers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/member")
    public ResponseEntity<Long> create(@RequestBody CreateMemberDto createMemberDto) {
        Long savedId = memberService.save(createMemberDto.getName(), createMemberDto.getAge(), createMemberDto.getPhone());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @PutMapping("/api/member/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UpdateMemberDto updateMemberDto) {
        memberService.update(id, updateMemberDto.getName(), updateMemberDto.getAge(), updateMemberDto.getPhone());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/member/{id}")
    public ResponseEntity<MemberRevisionsResponseDto> getRevison(@PathVariable("id") Long id) {
        List<Member> revisions = memberService.getRevisions(id);

        return ResponseEntity.ok().body(new MemberRevisionsResponseDto(revisions));
    }
}
