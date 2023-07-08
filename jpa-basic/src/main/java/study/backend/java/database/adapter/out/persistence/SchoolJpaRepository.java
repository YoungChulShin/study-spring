package study.backend.java.database.adapter.out.persistence;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.backend.java.database.domain.School;

interface SchoolJpaRepository extends JpaRepository<School, Long>, SchoolJpaRepositoryCustom {

  @Query("SELECT s FROM School s JOIN FETCH s.students")
  List<School> findAllWithStudents();

}
