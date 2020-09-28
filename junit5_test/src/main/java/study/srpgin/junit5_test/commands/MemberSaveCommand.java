package study.srpgin.junit5_test.commands;

import lombok.Data;
import study.srpgin.junit5_test.Member;

@Data
public class MemberSaveCommand {

    private String name;

    private int age;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .age(age)
                .build();
    }
}
