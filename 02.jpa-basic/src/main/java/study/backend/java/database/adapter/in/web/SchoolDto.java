package study.backend.java.database.adapter.in.web;


import jakarta.validation.constraints.NotEmpty;

class SchoolDto {

  record AddSchoolRequest(@NotEmpty String name) { }

  record AddSchoolResponse(Long schoolId) { }

  record SchoolInfo(Long schoolId, String name) { }

}
