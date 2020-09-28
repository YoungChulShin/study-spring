package study.srpgin.junit5_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.srpgin.junit5_test.commands.MemberSaveCommand;

import java.util.Optional;

@SpringBootTest
@DisplayName("MemberService 클래스")
class MemberServiceTest {

    private MemberService instance;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setup() {
        instance = new MemberService(memberRepository);
    }

    @Nested
    @DisplayName("Save 메서드는")
    class Save {

        @Nested
        @SpringBootTest
        @DisplayName("올바른 입력이 주어졌을 때")
        class validInput {

            @Test
            @Transactional
            @DisplayName("성공적으로 값을 저장한다")
            void shouldSuccess() {
                // given
                MemberSaveCommand memberSaveCommand = new MemberSaveCommand();
                memberSaveCommand.setName("youngchulshin");
                memberSaveCommand.setAge(30);

                // when
                Long savedId = instance.saveOne(memberSaveCommand);

                // then
                Optional<Member> findMember = memberRepository.findById(savedId);

                Assertions.assertThat(findMember.isPresent()).isTrue();
            }
        }
    }
}