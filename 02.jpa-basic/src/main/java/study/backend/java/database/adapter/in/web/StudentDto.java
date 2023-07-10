package study.backend.java.database.adapter.in.web;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

class StudentDto {

  static record AddStudentRequest(
      @NotEmpty String name,
      @NotNull Integer age,
      @NotNull Long schoolId) { }

  static record AddStudentResponse(Long studentId) { }

  static record StudentInfo(Long studentId, String name, Integer age, String schoolName) { }
}
