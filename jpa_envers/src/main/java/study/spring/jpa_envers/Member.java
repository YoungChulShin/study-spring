package study.spring.jpa_envers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Audited
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotAudited
    private String phone;

    private int age;

    public static Member create(String name, int age, String phone) {
        Member member = new Member();
        member.name = name;
        member.age = age;
        member.phone = phone;

        return member;
    }

    public void update(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}
