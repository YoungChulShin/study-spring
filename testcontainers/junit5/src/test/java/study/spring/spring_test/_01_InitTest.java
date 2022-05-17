package study.spring.spring_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

// @Testcontainers 애노테이션은 @Container 애노테이션을 찾아서 실행을 시킨다
@Testcontainers
public class _01_InitTest {

  // 일반 필드로 정의된 컨테이너는 테스트 마다 시작되고 종료된다
  // (Containers declared as instance fields will be started and stopped for every test method.)
  @Container
  private MySQLContainer testContainer = new MySQLContainer(
      DockerImageName.parse("mysql:8.0"))
      .withDatabaseName("spring_test")
      .withUsername("root")
      .withPassword("1323");

  // static 으로 정의되어 있으면 테스트 메서드 마다 컨테이너를 공유한다
  // @Container
  // private static final MySQLContainer MY_SQL_CONTAINER = new MySQLContainer();

  @Test
  void initTest() {
    // given, when
    String host = testContainer.getHost();
    String databaseName = testContainer.getDatabaseName();
    Integer firstMappedPort = testContainer.getFirstMappedPort();

    // then
    Assertions.assertThat(host).isEqualTo("localhost");
    Assertions.assertThat(databaseName).isEqualTo("spring_test");
    Assertions.assertThat(firstMappedPort).isNotNull();
    Assertions.assertThat(testContainer.isRunning()).isTrue();
  }
}
