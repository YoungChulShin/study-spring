package study.spring.webflux.webfluxtest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    System.out.println("필터 실행됨");

    HttpServletResponse servletResponse = (HttpServletResponse) response;
    servletResponse.setContentType("text/plain;charset=utf-8");

    PrintWriter out = servletResponse.getWriter();
    for (int i = 0; i < 5; i++) {
      out.print("응답:" + i);
      out.flush();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
