package study.spring.auth.bootserver.web.dto.board;

import lombok.Getter;
import study.spring.auth.bootserver.domain.board.Board;

import javax.validation.constraints.NotNull;

@Getter
public class BoardSaveRequestDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String author;

    public BoardSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
