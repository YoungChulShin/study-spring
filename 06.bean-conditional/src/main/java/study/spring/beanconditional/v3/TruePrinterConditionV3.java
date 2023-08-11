package study.spring.beanconditional.v3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring.beanconditional.v3.model.BooleanPrinterV3;
import study.spring.beanconditional.v3.model.TruePrinterV3;

@Configuration
@BooleanConditional(false)
public class TruePrinterConditionV3 {

  @Bean
  public BooleanPrinterV3 booleanPrinterV3() {
    return new TruePrinterV3();
  }
}
