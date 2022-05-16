package study.spring.spring_test.base;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

public abstract class BaseTestContainer {

  static final MySQLContainer MY_SQL_CONTAINER;

  static {
    MY_SQL_CONTAINER = new MySQLContainer();
    MY_SQL_CONTAINER.start();
  }

  @Container
  public static MySQLContainer<?> container = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
      .withDatabaseName("spring_test")
      .withUsername("root")
      .withPassword("1323")
      .withInitScript("db/init.sql");

}
