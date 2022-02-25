package study.spring.osiv_lazylaoding.presentation.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateStudentRequest {

  @NotBlank
  private String name;
  @Min(1)
  private Long age;
  @NotBlank
  private String schoolName;
}
