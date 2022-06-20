package study.spring.spring_test.domain.model.student;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

  Optional<Student> findByNameAndAge(String nane, int age);

}
