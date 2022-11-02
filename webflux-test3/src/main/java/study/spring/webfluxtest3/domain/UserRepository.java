package study.spring.webfluxtest3.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<Users, Long> {
}
