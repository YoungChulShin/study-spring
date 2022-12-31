package study.webflux.mvcsample.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = """
        SELECT s
        FROM   Student s
        JOIN   School sc ON s.school.id = sc.id
        WHERE  sc.id = :schoolId
    """)
    List<Student> findStudentBySchool(Long schoolId);
}
