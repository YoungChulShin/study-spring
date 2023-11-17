package study.spring.springtransactional.presentation;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring.springtransactional.domain.School;
import study.spring.springtransactional.service.SchoolService;

@RestController
public class SchoolController {

  private final SchoolService schoolService;

  public SchoolController(SchoolService schoolService) {
    this.schoolService = schoolService;
  }

  @PostMapping("/api/schools")
  public Long createSchool(@RequestBody @Valid CreateSchoolRequest request) {
    return schoolService.addSchool(request.name());
  }

  @GetMapping("/api/schools/{id}")
  public School getSchool(@PathVariable(value = "id") Long id) {
    return schoolService.findSchool(id);
  }
}

