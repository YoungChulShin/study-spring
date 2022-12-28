package study.webflux.webfluxsample.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long>, StudentCustomRepository {

//    @Query("""
//        SELECT s.id, s.name, s.age, s.gender, sc.name as schoolName
//        FROM  students s
//        INNER JOIN  schools sc
//                ON  s.school_id = sc.id
//""")
//    Flux<StudentInfo> findAllWithSchool();
}
