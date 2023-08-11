package study.spring.beanconditional.v2.model;

public class FalsePrinterV2 implements BooleanPrinterV2 {

  @Override
  public String print() {
    System.out.println("================================");
    System.out.println("false");
    System.out.println("================================");

    return "false";
  }
}
