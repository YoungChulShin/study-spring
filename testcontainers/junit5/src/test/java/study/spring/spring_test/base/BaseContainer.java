package study.spring.spring_test.base;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public class BaseContainer extends MySQLContainer<BaseContainer> {

  private static BaseContainer container;

  private BaseContainer() {
    super("mysql:8.0");
  }

  public static BaseContainer getInstance() {
    if(container == null) {
      container = new BaseContainer()
          .withDatabaseName("spring_test")
          .withUsername("root")
          .withPassword("1323")
          .withInitScript("db/init.sql");
    }

    return container;
  }

//  @DynamicPropertySource
//  static void properties(DynamicPropertyRegistry registry) {
//    registry.add("spring.datasource.url", container::getJdbcUrl);
//    registry.add("spring.datasource.username", container::getUsername);
//    registry.add("spring.datasource.password", container::getPassword);
//  }

  @Override
  public void start() {
    super.start();
  }

  @Override
  public void stop() {
    super.stop();
  }
}
