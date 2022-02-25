package study.spring.osiv_lazylaoding.presentation.model;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateSchoolRequest {

  @NotBlank
  private String name;
}
