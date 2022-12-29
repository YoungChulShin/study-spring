package study.webflux.webfluxsample.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentCustomRepository {

    Mono<Student> findStudentById(Long id);

    Flux<StudentInfo> findStudentInfos();

    Mono<StudentInfo> findStudentInfoById(Long id);
}
