package study.spring.jpa_test.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import study.spring.jpa_test.application.TeamService;
import study.spring.jpa_test.presentation.dto.TeamCreateDto;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<Long> create(@RequestBody TeamCreateDto teamCreateDto) {

        Long teamId = teamService.save(teamCreateDto.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(teamId);
    }

    @PostMapping("/team/{id}/stadium")
    public ResponseEntity<Long> updateStadium(
        @PathVariable(value = "id") Long teamId,
        @RequestParam(value = "name") String name,
        @RequestParam(value = "location") String location) {

        teamService.updateStadium(teamId, name, location);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/team/test")
    public ResponseEntity getTest() {

        teamService.isMyTeamMember(1L, Arrays.asList(1L, 2L));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/team/test2")
    public ResponseEntity getTest2() {

        teamService.teamTest();

        return ResponseEntity.ok().build();
    }
}
