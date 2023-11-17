package study.spring.springtransactional.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.springtransactional.domain.School;
import study.spring.springtransactional.domain.SchoolRepository;

@Service
public class SchoolService {

  private final SchoolRepository repository;

  public SchoolService(SchoolRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public Long addSchool(String name) {
    School initSchool = new School(name);
    School school = repository.save(initSchool);

    return school.getId();
  }

  @Transactional(readOnly = true)
  public School findSchool(Long id) {
    Optional<School> school = repository.findById(id);

    return school.orElse(null);
  }
}
