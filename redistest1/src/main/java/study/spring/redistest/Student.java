package study.spring.redistest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Student")
@AllArgsConstructor
@Getter
public class Student implements Serializable {

    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    private int grade;

    public void updateName(String newName) {
        this.name = newName;
    }
}
