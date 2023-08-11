package study.spring.beanconditional.v3.model;

public class FalsePrinterV3 implements BooleanPrinterV3 {

  @Override
  public String print() {
    System.out.println("================================");
    System.out.println("false");
    System.out.println("================================");

    return "false";
  }
}
