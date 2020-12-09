package study.spring.redistest.dto;

import lombok.Data;
import study.spring.redistest.Student;

@Data
public class FindStudentResponse {

    private String id;
    private String name;
    private Student.Gender gender;
    private int grade;

    public static FindStudentResponse from(Student entity) {
        FindStudentResponse response = new FindStudentResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setGender(entity.getGender());
        response.setGrade(entity.getGrade());

        return response;
    }
}
