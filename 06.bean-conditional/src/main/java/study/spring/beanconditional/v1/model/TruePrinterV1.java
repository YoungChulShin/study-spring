package study.spring.beanconditional.v1.model;

public class TruePrinterV1 implements BooleanPrinterV1 {

  @Override
  public String print() {
    System.out.println("================================");
    System.out.println("true");
    System.out.println("================================");

    return "true";
  }
}
