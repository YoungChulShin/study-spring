package study.spring.jpa_envers;

import lombok.Data;

@Data
public class UpdateMemberDto {

    private String name;

    private int age;

    private String phone;
}
