package study.backend.java.database.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.backend.java.database.application.port.out.StudentPort;
import study.backend.java.database.domain.Student;

@Repository
@RequiredArgsConstructor
class StudentPersistenceAdapter implements StudentPort {

  private final StudentJpaRepository studentJpaRepository;

  @Override
  public Student save(Student student) {
    return studentJpaRepository.save(student);
  }

  @Override
  public Student findById(Long id) {
    return studentJpaRepository.findById(id).orElse(null);
  }

  @Override
  public Student findWithSchoolById(Long id) {
    return studentJpaRepository.findWithSchoolById(id);
  }
}
