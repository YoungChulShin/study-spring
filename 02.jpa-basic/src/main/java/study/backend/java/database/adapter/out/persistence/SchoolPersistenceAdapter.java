package study.backend.java.database.adapter.out.persistence;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import study.backend.java.database.application.port.in.model.SchoolInfo;
import study.backend.java.database.application.port.in.model.StudentInfo;
import study.backend.java.database.application.port.out.SchoolReader;
import study.backend.java.database.application.port.out.SchoolWriter;
import study.backend.java.database.domain.School;

@Repository
@RequiredArgsConstructor
class SchoolPersistenceAdapter implements SchoolWriter, SchoolReader {

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
  public List<StudentInfo> findStudents(Long schoolId) {
    return schoolJpaRepository.findStudents(schoolId);
  }

  @Override
  public Page<StudentInfo> findStudents(Long schoolId, Pageable pageable) {
    return schoolJpaRepository.findStudents(schoolId, pageable);
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
