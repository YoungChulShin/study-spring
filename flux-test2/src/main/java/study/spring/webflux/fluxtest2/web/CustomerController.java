package study.spring.webflux.fluxtest2.web;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.spring.webflux.fluxtest2.domain.Customer;
import study.spring.webflux.fluxtest2.domain.CustomerRepository;

@RestController
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerRepository customerRepository;

  /**
   * 호출 순서
   * onSubscribe(FluxUsingWhen.UsingWhenSubscriber)
   * request(unbounded)
   * onNext(Customer(id=1, firstName=Jack, lastName=Bauer))
   * onNext(Customer(id=2, firstName=Chloe, lastName=O'Brian))
   * onNext(Customer(id=3, firstName=Kim, lastName=Bauer))
   * onNext(Customer(id=4, firstName=David, lastName=Palmer))
   * onNext(Customer(id=5, firstName=Michelle, lastName=Dessler))
   * onComplete()
   * 걀과 응답
   */
  @GetMapping("/customer")
  public Flux<Customer> findAll() {
    return customerRepository.findAll().log();
  }

  @GetMapping("/customer/{id}")
  public Mono<Customer> findById(@PathVariable Long id) {
    return customerRepository.findById(id).log();
  }


  @GetMapping("/flux")
  public Flux<Integer> flux() {
    return Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1)).log();
  }

  // on-next 할 때마다 flush한다
  @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<Integer> fluxstream() {
    return Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1)).log();
  }
}
