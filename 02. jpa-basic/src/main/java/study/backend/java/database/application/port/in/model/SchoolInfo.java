package study.backend.java.database.application.port.in.model;

import java.util.List;

public record SchoolInfo(
    Long id,
    String name,
    List<StudentInfo> studentInfos) {

}
