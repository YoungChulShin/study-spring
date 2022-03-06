package study.srpgin.junit5_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.srpgin.junit5_test.commands.MemberSaveCommand;
import study.srpgin.junit5_test.composed.IntegrationTest;

import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@IntegrationTest
@DisplayName("MemberService 클래스")
class MemberServiceTest {

    private MemberService instance;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setup() {
        instance = new MemberService(memberRepository);
    }

    @Test
    public void TestSuccess() {
        Assertions.assertThat(1).isEqualTo(1);
    }

    @Nested
    @DisplayName("Save 메서드는")
    class Save {

        @Nested
        @DisplayName("올바른 입력이 주어졌을 때")
        class validInput {

            @Test
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