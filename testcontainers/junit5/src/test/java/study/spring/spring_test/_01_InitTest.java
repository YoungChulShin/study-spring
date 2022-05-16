package study.spring.spring_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class _01_InitTest {

  @Container
  private MySQLContainer testContainer = new MySQLContainer(
      DockerImageName.parse("mysql:8.0"))
      .withDatabaseName("spring_test")
      .withUsername("root")
      .withPassword("1323");

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
