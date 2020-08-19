package study.spring.auth.bootserver.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import study.spring.auth.bootserver.domain.board.BoardRepository;
import study.spring.auth.bootserver.domain.board.BoardService;
import study.spring.auth.bootserver.domain.member.Member;
import study.spring.auth.bootserver.domain.member.MemberRepository;
import study.spring.auth.bootserver.domain.member.MemberService;
import study.spring.auth.bootserver.web.dto.board.BoardSaveRequestDto;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest
public class BoardApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void saveBoardWithoutAccessToken() throws Exception {

        // given
        BoardSaveRequestDto dto = new BoardSaveRequestDto("testTitle", "testContent", "testAuthor");

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/boards")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        // then
    }

}