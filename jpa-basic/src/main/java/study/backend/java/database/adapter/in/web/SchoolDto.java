package study.backend.java.database.adapter.in.web;


import jakarta.validation.constraints.NotEmpty;

class SchoolDto {

  static record AddSchoolRequest(@NotEmpty String name) { }

  static record AddSchoolResponse(Long schoolId) { }

  static record SchoolInfo(Long schoolId, String name) { }

}
