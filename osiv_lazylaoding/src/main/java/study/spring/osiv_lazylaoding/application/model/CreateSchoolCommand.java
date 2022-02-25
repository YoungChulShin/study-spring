package study.spring.osiv_lazylaoding.application.model;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class CreateSchoolCommand {

  private final String name;

  public CreateSchoolCommand(String name) {
    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("name");
    }
    this.name = name;
  }
}
