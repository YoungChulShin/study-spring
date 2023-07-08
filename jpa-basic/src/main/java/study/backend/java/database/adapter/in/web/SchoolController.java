package study.backend.java.database.adapter.in.web;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.backend.java.database.adapter.in.web.SchoolDto.AddSchoolRequest;
import study.backend.java.database.adapter.in.web.SchoolDto.AddSchoolResponse;
import study.backend.java.database.application.port.in.SchoolUseCase;
import study.backend.java.database.application.port.in.model.SchoolInfo;
import study.backend.java.database.application.port.in.model.StudentInfo;
import study.backend.java.database.common.response.CommonResponse;
import study.backend.java.database.domain.School;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
class SchoolController {

  private final SchoolUseCase schoolUseCase;

  @PostMapping
  CommonResponse addSchool(@RequestBody @Valid AddSchoolRequest request) {
    Long id = schoolUseCase.addSchool(request.name());
    return CommonResponse.success(new AddSchoolResponse(id));
  }

  @GetMapping("/{id}")
  CommonResponse findSchool(@PathVariable Long id) {
    School school = schoolUseCase.getSchool(id);
    return CommonResponse.success(new SchoolDto.SchoolInfo(school.getId(), school.getName()));
  }

  @GetMapping("/{id}/students")
  CommonResponse findStudents(@PathVariable Long id) {
    List<StudentInfo> students = schoolUseCase.findStudents(id);
    return CommonResponse.success(students);
  }

  @GetMapping
  CommonResponse findSchools() {
    List<SchoolInfo> schoolInfos = schoolUseCase.findSchools();
    return CommonResponse.success(schoolInfos);
  }

}
