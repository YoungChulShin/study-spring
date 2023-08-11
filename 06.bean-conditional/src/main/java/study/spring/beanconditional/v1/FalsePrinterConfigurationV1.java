package study.spring.beanconditional.v1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring.beanconditional.v1.model.BooleanPrinterV1;
import study.spring.beanconditional.v1.model.FalsePrinterV1;

@Configuration
@ConditionalOnProperty(value = "application.printer.boolean-printer.v1", havingValue = "false")
public class FalsePrinterConfigurationV1 {

  @Bean
  public BooleanPrinterV1 booleanPrinterV1() {
    return new FalsePrinterV1();
  }

}
