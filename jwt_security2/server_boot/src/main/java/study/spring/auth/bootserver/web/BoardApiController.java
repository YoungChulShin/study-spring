package study.spring.auth.bootserver.web;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import study.spring.auth.bootserver.domain.board.Board;
import study.spring.auth.bootserver.domain.board.BoardService;
import study.spring.auth.bootserver.web.dto.board.BoardResponseDto;
import study.spring.auth.bootserver.web.dto.board.BoardSaveRequestDto;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid BoardSaveRequestDto dto, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Long savedId = boardService.save(dto.toEntity());

        URI uri = WebMvcLinkBuilder.linkTo(BoardApiController.class).slash(savedId).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getBoard(@PathVariable Long id) {
        Board board = boardService.findOne(id);
        return ResponseEntity.ok(new BoardResponseDto(board));
    }

    @GetMapping
    public ResponseEntity getBoards() {
        List<BoardResponseDto> boardList = boardService.findAll().stream().map(x -> new BoardResponseDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok(boardList);
    }
}
