package study.spring.auth.bootserver.web.dto.board;

import study.spring.auth.bootserver.domain.board.Board;

public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public BoardResponseDto (Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
