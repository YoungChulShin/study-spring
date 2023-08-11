package study.spring.beanconditional.v3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring.beanconditional.v3.model.BooleanPrinterV3;
import study.spring.beanconditional.v3.model.FalsePrinterV3;
import study.spring.beanconditional.v3.model.TruePrinterV3;

@Configuration
@BooleanConditional(true)
public class FalsePrinterConditionV3 {

  @Bean
  public BooleanPrinterV3 booleanPrinterV3() {
    return new FalsePrinterV3();
  }
}
