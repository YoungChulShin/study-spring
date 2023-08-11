package study.spring.beanconditional.model;

public class TruePrinter implements BooleanPrinter {

  @Override
  public void print() {
    System.out.println("================================");
    System.out.println("true");
    System.out.println("================================");
  }
}
