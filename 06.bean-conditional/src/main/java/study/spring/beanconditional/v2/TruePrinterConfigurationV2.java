package study.spring.beanconditional.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import study.spring.beanconditional.v1.model.BooleanPrinterV1;
import study.spring.beanconditional.v1.model.TruePrinterV1;
import study.spring.beanconditional.v2.model.BooleanPrinterV2;
import study.spring.beanconditional.v2.model.TruePrinterV2;

@Configuration
@Conditional(TruePrinterConditionV2.class)
public class TruePrinterConfigurationV2 {

  @Bean
  public BooleanPrinterV2 booleanPrinterV2() {
    return new TruePrinterV2();
  }
}
