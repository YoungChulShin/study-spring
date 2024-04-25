package study.spring.dbconnection.mysqlconnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MysqlConnectorApplication {

  public static void main(String[] args) {
    SpringApplication.run(MysqlConnectorApplication.class, args);
  }

}
