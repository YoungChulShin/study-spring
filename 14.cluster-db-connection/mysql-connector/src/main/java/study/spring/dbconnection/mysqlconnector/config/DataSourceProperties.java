package study.spring.dbconnection.mysqlconnector.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.datasource.cluster")
public class DataSourceProperties {

  private final String username;
  private final String password;
  private final String driverClassName;
  private final WriterDb writer;
  private final ReaderDb reader;

  public DataSourceProperties(
      String username,
      String password,
      String driverClassName,
      WriterDb writer,
      ReaderDb reader) {
    this.username = username;
    this.password = password;
    this.driverClassName = driverClassName;
    this.writer = writer;
    this.reader = reader;
  }


  @Getter
  @RequiredArgsConstructor
  public static class WriterDb {
    private final String url;
  }

  @Getter
  @RequiredArgsConstructor
  public static class ReaderDb {
    private final String url;
  }

}
