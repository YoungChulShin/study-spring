package study.backend.java.database.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.backend.java.database.domain.Student;

interface StudentJpaRepository
    extends JpaRepository<Student, Long>, StudentJpaRepositoryCustom {

  @Query("SELECT s FROM Student s JOIN FETCH s.school WHERE s.id = :id")
  Student findWithSchoolById(@Param("id") Long id);
}
