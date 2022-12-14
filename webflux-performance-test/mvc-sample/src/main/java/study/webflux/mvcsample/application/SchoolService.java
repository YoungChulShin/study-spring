package study.webflux.mvcsample.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.webflux.mvcsample.domain.School;
import study.webflux.mvcsample.domain.SchoolRepository;

@Service
@Transactional
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public SchoolInfo createSchool(CreateSchoolCommand command) {
        if (schoolRepository.findByName(command.name()).isPresent()) {
            throw new RuntimeException("Already exists");
        }

        var school = new School(command.name());
        schoolRepository.save(school);

        return SchoolInfo.from(school);
    }
}
