package study.spring.jpa_envers;

import lombok.Data;

@Data
public class CreateMemberDto {

    private String name;

    private int age;

    private String phone;
}
