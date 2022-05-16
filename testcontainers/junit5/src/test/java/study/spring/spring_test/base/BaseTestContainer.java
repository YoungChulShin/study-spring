package study.spring.spring_test.base;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class BaseTestContainer {

  static final MySQLContainer MY_SQL_CONTAINER;

  static {
    MY_SQL_CONTAINER = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
        .withDatabaseName("spring_test")
        .withUsername("root")
        .withPassword("1323")
        .withInitScript("db/init.sql");
    MY_SQL_CONTAINER.start();
  }

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
    registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
  }
}
