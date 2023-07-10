package study.backend.java.database.adapter.out.persistence;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.backend.java.database.application.port.in.model.SchoolInfo;
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

  @Override
  public List<School> findAll() {
    return schoolJpaRepository.findAll();
  }

  @Override
  public List<School> findAllWithStudents() {
    return schoolJpaRepository.findAllWithStudents();
  }

  @Override
  public List<SchoolInfo> findAllSchoolInfos() {
    return schoolJpaRepository.findAllSchoolInfos();
  }
}
