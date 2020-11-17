package study.spring.jpa_test.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import study.spring.jpa_test.application.MemberService;
import study.spring.jpa_test.presentation.dto.MemberCreateDto;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity create(@RequestBody MemberCreateDto dto) {
        Long savedId = memberService.save(dto.getName(), dto.getTeamId());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }
}
