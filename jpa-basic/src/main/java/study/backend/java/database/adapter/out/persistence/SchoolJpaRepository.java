package study.backend.java.database.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import study.backend.java.database.domain.School;

interface SchoolJpaRepository extends JpaRepository<School, Long> {

}
