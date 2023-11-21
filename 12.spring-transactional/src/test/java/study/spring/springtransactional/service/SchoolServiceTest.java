package study.spring.springtransactional.service;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import study.spring.springtransactional.domain.School;
import study.spring.springtransactional.domain.SchoolRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Rollback
class SchoolServiceTest {

  @Autowired
  private SchoolService schoolService;

  @Autowired
  private SchoolRepository schoolRepository;

  @Test
  void 학교를_저장합니다() throws Exception {
    String name = "테스트학교";
    Long schoolId = schoolService.addSchool(name);

    Assertions.assertThat(schoolRepository.findById(schoolId)).isPresent();
  }

  @Test
  void 언체크드_예외_발생시_롤백_됩니다() throws Exception {
    String name = "롤백학교";
    Throwable thrown = catchThrowable(() -> schoolService.addSchool(name));

    Optional<School> findSchool = schoolRepository.findById(Long.valueOf(thrown.getMessage()));
    Assertions.assertThat(findSchool).isNotPresent();
  }

  @Test
  void 체크드_예외_발생시_롤백되지_않습니다() {
    String name = "커밋학교";
    Throwable thrown = catchThrowable(() -> schoolService.addSchool(name));

    Optional<School> findSchool = schoolRepository.findById(Long.valueOf(thrown.getMessage()));
    Assertions.assertThat(findSchool).isPresent();
  }
}