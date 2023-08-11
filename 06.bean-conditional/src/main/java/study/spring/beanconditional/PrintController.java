package study.spring.beanconditional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.beanconditional.v1.model.BooleanPrinterV1;
import study.spring.beanconditional.v2.model.BooleanPrinterV2;

@RestController
public class PrintController {

  private final BooleanPrinterV1 printerV1;
  private final BooleanPrinterV2 printerV2;

  public PrintController(
      BooleanPrinterV1 printerV1,
      BooleanPrinterV2 printerV2) {
    this.printerV1 = printerV1;
    this.printerV2 = printerV2;
  }

  @PostMapping("api/v1/print")
  public String printV1() {
    return printerV1.print();
  }

  @PostMapping("api/v2/print")
  public String printV2() {
    return printerV2.print();
  }
}
