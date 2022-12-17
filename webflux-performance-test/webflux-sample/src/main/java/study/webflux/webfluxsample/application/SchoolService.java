package study.webflux.webfluxsample.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import study.webflux.webfluxsample.domain.School;
import study.webflux.webfluxsample.domain.SchoolRepository;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    public Mono<SchoolInfo> createSchool(CreateSchoolCommand command) {
        return schoolRepository.findByName(command.name())
                .flatMap(e -> Mono.error(new RuntimeException("Already exists")))
                .switchIfEmpty(Mono.just(new School(command.name())))
                .cast(School.class)
                .flatMap(schoolRepository::save)
                .map(SchoolInfo::from);
    }
}
