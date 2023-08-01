package study.spring.configurationproperties.config.model;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MyApplicationInfo를 Record를 이용해서 구현합니다.
 */
@ConfigurationProperties(prefix = "my-application-v3")
public record MyApplicationInfoV3(String name,
                                  String version,
                                  String releaseDate,
                                  List<Developer> developers) {

  public record Developer(String name,
                          String email,
                          String company,
                          String phone,
                          List<String> skills) { }
}
