package study.spring.beanconditional.model;

public class FalsePrinter implements BooleanPrinter {

  @Override
  public void print() {
    System.out.println("================================");
    System.out.println("false");
    System.out.println("================================");
  }
}
