package study.webflux.webfluxsample.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import study.webflux.webfluxsample.application.CreateSchoolCommand;
import study.webflux.webfluxsample.domain.SchoolInfo;
import study.webflux.webfluxsample.application.SchoolService;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/api/schools")
    public Mono<SchoolInfo> createSchool(@RequestBody CreateSchoolDto request) {
        return schoolService.createSchool(new CreateSchoolCommand(request.name())).log();
    }
}
