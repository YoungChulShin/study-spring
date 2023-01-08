package study.spring.rediscache.ui.dto;

import lombok.Data;
import study.spring.rediscache.domain.Student;

@Data
public class StudentResponse {

    private Long id;

    private String name;

    private int age;

    public static StudentResponse from(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setAge(student.getAge());

        return response;
    }
}
