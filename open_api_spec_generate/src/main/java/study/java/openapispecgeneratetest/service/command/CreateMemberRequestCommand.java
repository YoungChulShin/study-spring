package study.java.openapispecgeneratetest.service.command;

import lombok.Getter;
import study.java.openapispecgeneratetest.entity.Gender;
import study.java.openapispecgeneratetest.entity.Member;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class CreateMemberRequestCommand {

    private String name;

    private int age;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.Male;

    public CreateMemberRequestCommand(String name, int age, String phone, String gender) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.gender = Gender.fromString(gender);
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .age(age)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
