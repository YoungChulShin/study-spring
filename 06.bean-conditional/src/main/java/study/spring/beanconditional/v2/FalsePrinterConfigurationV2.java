package study.spring.beanconditional.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import study.spring.beanconditional.v2.model.BooleanPrinterV2;
import study.spring.beanconditional.v2.model.FalsePrinterV2;

@Configuration
@Conditional(FalsePrinterConditionV2.class)
public class FalsePrinterConfigurationV2 {

  @Bean
  public BooleanPrinterV2 booleanPrinterV2() {
    return new FalsePrinterV2();
  }

}
