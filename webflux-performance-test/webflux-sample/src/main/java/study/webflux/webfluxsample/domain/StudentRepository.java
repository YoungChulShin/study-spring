package study.webflux.webfluxsample.domain;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    @Query("""
        SELECT s.id, s.name, s.age, s.gender, sc.name as schoolName
        FROM  students s
        INNER JOIN  schools sc
                ON  s.school_id = sc.id
""")
    Flux<StudentInfo> findAllWithSchool();
}
