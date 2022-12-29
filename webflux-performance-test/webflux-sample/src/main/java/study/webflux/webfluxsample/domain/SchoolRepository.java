package study.webflux.webfluxsample.domain;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface SchoolRepository extends R2dbcRepository<School, Long> {

    Mono<School> findByName(String name);
}
