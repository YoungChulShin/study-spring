package study.webflux.webfluxsample.domain;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    @Query("""
        SELECT s.id, s.name, s.age, s.gender, sc.name
        FROM  student s
        INNER JOIN  school sc
                ON  s.schoolId = sc.id
""")
    Flux<StudentInfo> findAllWithSchool();
}
