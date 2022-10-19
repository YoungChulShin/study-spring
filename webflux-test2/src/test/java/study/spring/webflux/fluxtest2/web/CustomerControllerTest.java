package study.spring.webflux.fluxtest2.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import study.spring.webflux.fluxtest2.domain.Customer;
import study.spring.webflux.fluxtest2.domain.CustomerRepository;

//통합테스트
//@SpringBootTest
//@AutoConfigureWebTestClient

//컨트롤러테스트
@WebFluxTest
// 필요한 항목을 빈으로 등록한다 @Import()
class CustomerControllerTest {

  @Autowired
  private WebTestClient webClient;

  @MockBean
  private CustomerRepository customerRepository;

  @Test
  void 한건찾기_테스트() {
    Customer customer = new Customer("Jack", "Bauer");

    Mockito.when(customerRepository.findById(1L))
        .thenReturn(Mono.just(customer));

    webClient.get().uri("/customer/{id}", 1L)
        .exchange()
        .expectBody(Customer.class).isEqualTo(customer);
  }

  @Test
  void 한건찾기_테스트2() {
    Customer customer = new Customer("Jack", "Bauer");

    Mockito.when(customerRepository.findById(1L))
        .thenReturn(Mono.just(customer));

    webClient.get().uri("/customer/{id}", 1L)
        .exchange()
        .expectBody()
        .jsonPath("$.firstName").isEqualTo("Jack")
        .jsonPath("$.lastName").isEqualTo("Bauer");
  }
}