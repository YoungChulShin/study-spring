package study.spring.beanconditional.v1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.beanconditional.v1.model.BooleanPrinterV1;

@RestController
public class PrintControllerV1 {

  private final BooleanPrinterV1 printer;

  public PrintControllerV1(BooleanPrinterV1 printer) {
    this.printer = printer;
  }

  @PostMapping("api/v1/print")
  public String print() {
    return printer.print();
  }
}
