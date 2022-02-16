package study.spring.retry_transactional.presentation;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class RegisterOrderRequest {

  @NotEmpty
  private String orderNumber;
}
