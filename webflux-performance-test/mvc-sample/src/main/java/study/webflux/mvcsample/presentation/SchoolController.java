package study.webflux.mvcsample.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.webflux.mvcsample.application.CreateSchoolCommand;
import study.webflux.mvcsample.application.SchoolInfo;
import study.webflux.mvcsample.application.SchoolService;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/api/schools")
    public SchoolInfo createSchool(@RequestBody CreateSchoolDto request) {
        return schoolService.createSchool(new CreateSchoolCommand(request.name()));
    }
}
