package study.spring.redistest.dto;

import lombok.Data;
import study.spring.redistest.Student;

@Data
public class UpdateStudentResponse {

    private String id;
    private String name;
    private Student.Gender gender;
    private int grade;

    public static UpdateStudentResponse from(Student entity) {
        UpdateStudentResponse response = new UpdateStudentResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setGender(entity.getGender());
        response.setGrade(entity.getGrade());

        return response;
    }
}
