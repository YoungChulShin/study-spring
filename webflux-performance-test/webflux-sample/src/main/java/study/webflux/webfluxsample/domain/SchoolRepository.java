package study.webflux.webfluxsample.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SchoolRepository extends ReactiveCrudRepository<School, Long> {

    Mono<School> findByName(String name);
}
