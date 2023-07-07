package study.backend.java.database.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.backend.java.database.application.port.out.SchoolPort;
import study.backend.java.database.domain.School;

@Repository
@RequiredArgsConstructor
class SchoolPersistenceAdapter implements SchoolPort {

  private final SchoolJpaRepository schoolJpaRepository;

  @Override
  public School save(School school) {
    return schoolJpaRepository.save(school);
  }

  @Override
  public School findById(Long id) {
    return schoolJpaRepository.findById(id).orElse(null);
  }
}
