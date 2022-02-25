package study.spring.osiv_lazylaoding.presentation;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring.osiv_lazylaoding.application.SchoolService;
import study.spring.osiv_lazylaoding.application.model.CreateSchoolCommand;
import study.spring.osiv_lazylaoding.presentation.model.CreateSchoolRequest;

@RestController
@RequiredArgsConstructor
public class SchoolController {

  private final SchoolService schoolService;

  @PostMapping("/api/school")
  public Long createSchool(@RequestBody @Valid CreateSchoolRequest request) {
    return schoolService.createSchool(new CreateSchoolCommand(request.getName()));
  }
}
