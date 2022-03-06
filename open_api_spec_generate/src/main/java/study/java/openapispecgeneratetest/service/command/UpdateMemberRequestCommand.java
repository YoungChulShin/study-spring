package study.java.openapispecgeneratetest.service.command;

import lombok.Getter;
import study.java.openapispecgeneratetest.entity.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class UpdateMemberRequestCommand {

    private Long id;

    private String name;

    private int age;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.Male;

    public UpdateMemberRequestCommand(Long id, String name, int age, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.gender = Gender.fromString(gender);
    }
}
