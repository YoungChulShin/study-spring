package study.spring.circuitbreaker.my.infra;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerEventConfig {

  private final Logger logger = LoggerFactory.getLogger(CircuitBreakerEventConfig.class);
  private final CircuitBreakerRegistry registry;

  public CircuitBreakerEventConfig(CircuitBreakerRegistry registry) {
    this.registry = registry;
  }

  @Bean
  public CircuitBreaker uuidServiceCircuitBreakerCustomizer() {
    CircuitBreaker circuitBreaker = registry.find("uuidService").orElse(null);
    if (circuitBreaker == null) {
      return null;
    }

    // uuidService : State transition from CLOSED to OPEN

    circuitBreaker.getEventPublisher()
        .onStateTransition(event -> {
          logger.info(event.getCircuitBreakerName() + " : " + event.getStateTransition());
        });

    return circuitBreaker;
  }

}
