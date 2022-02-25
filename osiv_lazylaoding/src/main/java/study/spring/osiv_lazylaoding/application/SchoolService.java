package study.spring.osiv_lazylaoding.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.osiv_lazylaoding.application.model.CreateSchoolCommand;
import study.spring.osiv_lazylaoding.domain.School;
import study.spring.osiv_lazylaoding.domain.SchoolRepository;

@Service
@RequiredArgsConstructor
public class SchoolService {

  private final SchoolRepository schoolRepository;

  @Transactional
  public Long createSchool(CreateSchoolCommand command) {
    School initSchool = new School(command.getName());
    School school = schoolRepository.save(initSchool);

    return school.getId();
  }
}
