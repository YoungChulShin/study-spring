package study.backend.java.database.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.backend.java.database.adapter.in.web.StudentDto.AddStudentRequest;
import study.backend.java.database.adapter.in.web.StudentDto.AddStudentResponse;
import study.backend.java.database.application.port.in.StudentUseCase;
import study.backend.java.database.application.port.in.model.StudentInfo;
import study.backend.java.database.common.response.CommonResponse;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {

  private final StudentUseCase studentUseCase;

  @PostMapping
  CommonResponse addStudent(@RequestBody @Valid AddStudentRequest request) {
    Long schoolId = studentUseCase.addStudent(request.name(), request.age(), request.schoolId());
    return CommonResponse.success(new AddStudentResponse(schoolId));
  }

  @GetMapping("/{id}")
  CommonResponse findStudent(@PathVariable Long id) {
    StudentInfo studentInfo = studentUseCase.getStudent(id);
    return CommonResponse.success(new StudentDto.StudentInfo(
        studentInfo.id(),
        studentInfo.name(),
        studentInfo.age(),
        studentInfo.schoolName()));
  }
}
