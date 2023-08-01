package study.spring.configurationproperties.config.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

/**
 * MyApplicationInfo를 생성자 주입 방법으로 변경합니다.
 */
@ConfigurationProperties(prefix = "my-application-v2")
public class MyApplicationInfoV2 {

  private final String name;
  private final String version;
  private final String releaseDate;
  private final List<Developer> developers;

  public MyApplicationInfoV2(
      String name,
      String version,
      String releaseDate,
      List<Developer> developers) {
    this.name = name;
    this.version = version;
    this.releaseDate = releaseDate;
    this.developers = developers;
  }

  public String getName() {
    return name;
  }

  public String getVersion() {
    return version;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public List<Developer> getDevelopers() {
    return developers;
  }

  public static class Developer {

    private final String name;
    private final String email;
    private final String company;
    private final String phone;
    private final List<String> skills;

    public Developer(
        String name,
        String email,
        String company,
        String phone,
        List<String> skills) {
      this.name = name;
      this.email = email;
      this.company = company;
      this.phone = phone;
      this.skills = skills;
    }

    public String getName() {
      return name;
    }

    public String getEmail() {
      return email;
    }

    public String getCompany() {
      return company;
    }

    public String getPhone() {
      return phone;
    }

    public List<String> getSkills() {
      return skills;
    }
  }
}
