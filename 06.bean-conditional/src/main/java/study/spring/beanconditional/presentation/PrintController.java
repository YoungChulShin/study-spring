package study.spring.beanconditional.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.beanconditional.model.BooleanPrinter;

@RestController
public class PrintController {

  private final BooleanPrinter printer;

  public PrintController(BooleanPrinter printer) {
    this.printer = printer;
  }

  @PostMapping("/print")
  public void print() {
    printer.print();
  }
}
