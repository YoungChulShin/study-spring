package study.spring.webflux.webfluxtest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class MyFilter2 implements Filter {

  private EventNotify eventNotify;

  public MyFilter2(EventNotify eventNotify) {
    this.eventNotify = eventNotify;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    System.out.println("필터2 실행됨");

    eventNotify.add("새로운 데이터");
  }
}
