package study.spring.beanconditional.v1.model;

public class FalsePrinterV1 implements BooleanPrinterV1 {

  @Override
  public String print() {
    System.out.println("================================");
    System.out.println("false");
    System.out.println("================================");

    return "false";
  }
}
