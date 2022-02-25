package study.spring.osiv_lazylaoding.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {

  Optional<School> findOneByName(String name);
}
