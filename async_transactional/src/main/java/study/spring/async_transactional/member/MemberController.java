package study.spring.async_transactional.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("member")
    public ResponseEntity createMember(@RequestBody MemberCreateDto dto) {
        Long savedId = memberService.saveOne(dto.getName(), dto.getTeamName());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }
}
