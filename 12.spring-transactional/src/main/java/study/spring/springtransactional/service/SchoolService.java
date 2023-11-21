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
  public Long addSchool(String name) throws Exception {
    School initSchool = new School(name);
    School school = repository.save(initSchool);

    if (name.equals("롤백학교")) {
      throw new RuntimeException(String.valueOf(school.getId()));
    } else if (name.equals("커밋학교")) {
      throw new Exception(String.valueOf(school.getId()));
    }

    return school.getId();
  }

  @Transactional(readOnly = true)
  public School findSchool(Long id) {
    Optional<School> school = repository.findById(id);

    return school.orElse(null);
  }
}
