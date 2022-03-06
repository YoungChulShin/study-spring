package study.spring.redistest.dto;

import lombok.Data;

@Data
public class UpdateStudentNameRequest {
    private String id;
    private String name;
}
