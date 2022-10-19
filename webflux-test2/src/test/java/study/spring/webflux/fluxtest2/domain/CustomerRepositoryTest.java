package study.spring.webflux.fluxtest2.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

@DataR2dbcTest
public class CustomerRepositoryTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  void 한건찾기_테스트() {

  }
}
