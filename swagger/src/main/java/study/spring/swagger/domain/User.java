package study.spring.swagger.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {

    @Id
    private Long id;
    private String firstName;
    private int age;
    private String email;
}
