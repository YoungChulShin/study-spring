package study.spring.beanconditional.v2.model;

public class TruePrinterV2 implements BooleanPrinterV2 {

  @Override
  public String print() {
    System.out.println("================================");
    System.out.println("true");
    System.out.println("================================");

    return "true";
  }
}
