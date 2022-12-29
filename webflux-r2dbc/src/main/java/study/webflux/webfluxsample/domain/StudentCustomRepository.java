package study.webflux.webfluxsample.domain;

import reactor.core.publisher.Flux;

public interface StudentCustomRepository {

    Flux<StudentInfo> findAllWithSchool();
}
