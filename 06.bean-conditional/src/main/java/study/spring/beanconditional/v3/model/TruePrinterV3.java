package study.spring.beanconditional.v3.model;

public class TruePrinterV3 implements BooleanPrinterV3 {

  @Override
  public String print() {
    System.out.println("================================");
    System.out.println("true");
    System.out.println("================================");

    return "true";
  }
}
