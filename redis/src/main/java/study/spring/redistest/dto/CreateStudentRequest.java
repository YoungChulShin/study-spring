package study.spring.redistest.dto;

import lombok.Data;
import study.spring.redistest.Student;

@Data
public class CreateStudentRequest {
    private String id;
    private String name;
    private Student.Gender gender;
    private int grade;
}
